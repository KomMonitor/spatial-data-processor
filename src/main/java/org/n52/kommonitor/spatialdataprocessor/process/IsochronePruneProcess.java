package org.n52.kommonitor.spatialdataprocessor.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.geotools.data.geojson.GeoJSONReader;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.n52.kommonitor.dataloader.FeatureDataSource;
import org.n52.kommonitor.dataloader.FeatureLoader;
import org.n52.kommonitor.dataloader.FeatureLoaderRepository;
import org.n52.kommonitor.models.*;
import org.n52.kommonitor.spatialdataprocessor.operations.OperationException;
import org.n52.kommonitor.spatialdataprocessor.operations.SpatialOperationUtils;
import org.n52.kommonitor.spatialdataprocessor.util.FeatureUtils;
import org.n52.kommonitor.spatialdataprocessor.util.IsochroneUtils;
import org.n52.kommonitor.spatialdataprocessor.util.datamanagement.DataManagementClient;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implements a process to cut isochrones with spatial units
 */
public class IsochronePruneProcess implements Process<IsochronePruneProcessType> {

    public static final UUID id =
            UUID.nameUUIDFromBytes(IsochronePruneProcess.class.getName().getBytes(StandardCharsets.UTF_8));

    private static final String DATE_PROP_PREFIX = "DATE_";
    private static final String ID_PROP_NAME = "ID";
    private static final Logger LOGGER = LoggerFactory.getLogger(IsochronePruneProcess.class);
    private final IsochronePruneProcessType parameters;
    private final DataManagementClient dmc;

    private final SpatialOperationUtils operationUtils;

    private final FeatureUtils featureUtils;

    private final IsochroneUtils isochroneUtils;

    private final FeatureDataSource dataSource;

    private final FeatureLoaderRepository repository;

    private final FeatureLoader featureLoader;

    private final Optional<String> authHeader;

    public IsochronePruneProcess(IsochronePruneProcessType parameters, DataManagementClient dmc,
                                 SpatialOperationUtils operationUtils, FeatureUtils featureUtils,
                                 IsochroneUtils isochroneUtils, FeatureDataSource dataSource,
                                 FeatureLoaderRepository repository,
                                 Map<String, Object> metadata) {
        this.parameters = parameters;
        this.dmc = dmc;
        this.operationUtils = operationUtils;
        this.featureUtils = featureUtils;
        this.isochroneUtils = isochroneUtils;
        this.dataSource = dataSource;
        this.repository = repository;
        this.featureLoader = repository.getFeatureLoader(dataSource.getType()).get();

        this.authHeader = metadata.containsKey("authHeader")?
                Optional.of(metadata.get("authHeader").toString()) : Optional.empty();

    }


    @Override
    public Object execute() throws Exception {
        LOGGER.info("Start executing IsochronePrune process.");
        List<UUID> indicatorList = parameters.getIndicator();
        UUID spatialUnitId = parameters.getSpatialUnit();
        String isochrones = (String) parameters.getIsochrones();
        LocalDate date = parameters.getDate();

        // 1) Fetch geometries only for the specified SpatialUnit and create a FeatureCollection for it as well
        // as for the provided isochrones
        LOGGER.debug("Fetch geometries for SpatialUnit {}", spatialUnitId);
        ObjectNode spatialUnit = dmc.getSpatialUnitGeoJSON(spatialUnitId, authHeader);
        SimpleFeatureCollection spatialUnitFc = GeoJSONReader.parseFeatureCollection(spatialUnit.toString());
        SimpleFeatureCollection isochronesFc = GeoJSONReader.parseFeatureCollection(isochrones);

        List<IsochronePruneProcessResultType> result = calculateIsochronePrune(indicatorList, spatialUnitId, date, isochrones, isochronesFc, spatialUnitFc);
        LOGGER.info("Successfully finished IsochronePrune process.");
        return result;
    }

    protected List<IsochronePruneProcessResultType> calculateIsochronePrune(List<UUID> indicatorList,
                                                                            UUID spatialUnitId,
                                                                            LocalDate date,
                                                                            String isochrones,
                                                                            SimpleFeatureCollection isochronesFc,
                                                                            SimpleFeatureCollection spatialUnitFc) throws OperationException {
        // 2) Fetch indicators with timeseries only without geometry and store it within a lookup HashMap by indexing
        // it with the SpatialUnit Feature IDs
        LOGGER.info("Create lookup map for indicator summary");
        IndicatorSummary indicatorSummary = createIndicatorSummary(indicatorList, spatialUnitId, date);

        Map<String, Map<UUID, List<IndicatorValue>>> lookupMap = indicatorSummary.lookupMap;
        Map<UUID, Map<LocalDate, Double>> totalIndicatorScore = indicatorSummary.totalIndicatorScore;

        // 3) Calculate intersections between POIs (isochrones) and SpatialUnit features
        LOGGER.info("Calculate single isochrone coverage scores with weighting '{}'", parameters.getWeighting());
        Map<String, Map<String, Float>> intersectionMap = calculateWeightedIntersection(isochronesFc, spatialUnitFc, parameters.getWeighting());

        // 4) Calculate indicator coverages for each combination of isochrones and SpatialUnits that intersect
        List<IsochronePruneProcessResultType> resultList = new ArrayList<>();
        totalIndicatorScore.keySet().forEach(i -> {
            IsochronePruneProcessResultType result = new IsochronePruneProcessResultType();
            result.setIndicatorId(i);
            List<PoiCoverageType> poiCoverageList = new ArrayList<>();
            intersectionMap.forEach((pk, pv) -> {
                PoiCoverageType poiCoverage = new PoiCoverageType();
                poiCoverage.setPoiFeatureId(pk);
                List<SpatialUnitCoverageType> spatialUnitCoverageList = new ArrayList<>();
                HashMap<LocalDate, Double> coverageSumMap = new HashMap<>();
                pv.forEach((sk, sv) -> {
                    SpatialUnitCoverageType spatialUnitCoverage = new SpatialUnitCoverageType();
                    spatialUnitCoverage.setSpatialUnitFeatureId(sk);
                    List<IndicatorCoverageValueType> coverageValueList = new ArrayList<>();
                    lookupMap.get(sk).get(i).forEach(iv -> {
                        // Calculate the single SpatialUnit coverage for the current isochrone
                        float absoluteCoverage = (float) (iv.value * sv);
                        IndicatorCoverageValueType coverageValue = new IndicatorCoverageValueType();
                        coverageValue.setDate(iv.date);
                        coverageValue.setRelativeCoverage(sv);
                        coverageValue.setAbsoluteCoverage(absoluteCoverage);
                        coverageValueList.add(coverageValue);

                        if (!coverageSumMap.containsKey(iv.date)) {
                            coverageSumMap.put(iv.date, 0.);
                        }
                        double coverageSum = coverageSumMap.get(iv.date) + absoluteCoverage;
                        coverageSumMap.put(iv.date, coverageSum);
                    });
                    spatialUnitCoverage.setCoverage(coverageValueList);
                    spatialUnitCoverageList.add(spatialUnitCoverage);
                });
                poiCoverage.setSpatialUnitCoverage(spatialUnitCoverageList);
                List<IndicatorCoverageValueType> overalCoverageList = coverageSumMap.entrySet().stream()
                        .map(e -> new IndicatorCoverageValueType()
                                .date(e.getKey())
                                .relativeCoverage(e.getValue().floatValue() / totalIndicatorScore.get(i).get(e.getKey()).floatValue())
                                .absoluteCoverage(e.getValue().floatValue()))
                        .collect(Collectors.toList());
                poiCoverage.setOverallCoverage(overalCoverageList);
                poiCoverageList.add(poiCoverage);
            });
            result.setPoiCoverage(poiCoverageList);

            //calculate summed up overall indicator coverage for all isochrones
            List<OverallCoverageType> overallScore = Collections.emptyList();
            try {
                LOGGER.info("Calculate overall coverage score with weighting '{}'", parameters.getWeighting());
                overallScore = calculateOverallScore(spatialUnitFc, isochronesFc, isochrones, i, totalIndicatorScore, parameters.getWeighting());
            } catch (OperationException | JsonProcessingException e) {
                LOGGER.error("Could not calculate overall indicator coverage for indicator {} due to error: {}", i, e.getMessage());
            }
            result.setOverallCoverage(overallScore);
            resultList.add(result);
        });

        return resultList;
    }

    private List<OverallCoverageType> calculateOverallScore(SimpleFeatureCollection spatialUnitFc,
                                                            SimpleFeatureCollection isochronesFc,
                                                            String isochrones,
                                                            UUID indicatorId,
                                                            Map<UUID, Map<LocalDate, Double>> totalIndicatorScore,
                                                            IsochronePruneProcessType.WeightingEnum weighting) throws OperationException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode isochroneNode = mapper.readTree(isochrones);
        List<Double> rangeList = isochroneUtils.getRanges(isochroneNode);
        Geometry combinedSpatialUnitGeometry = operationUtils.combineGeometries(spatialUnitFc);

        List<OverallCoverageType> resultList = new ArrayList<>();
        // Calculate the total coverage for each isochrone range group
        rangeList.forEach(r -> {
            try {
                OverallCoverageType rangeCoverage = switch (weighting) {
                    case RESIDENTIAL_AREAS -> calculateResidentialAreaWeightedRangeCoverage(
                            combinedSpatialUnitGeometry,
                            isochronesFc,
                            indicatorId,
                            totalIndicatorScore,
                            r
                    );
                    default -> calculateSimpleWeightedRangeCoverage(
                            combinedSpatialUnitGeometry,
                            isochronesFc,
                            indicatorId,
                            totalIndicatorScore,
                            r
                    );
                };
                resultList.add(rangeCoverage);
            } catch (OperationException e) {
                LOGGER.warn(String.format("Could not calculate overall coverage for indicator %s and range %s.", indicatorId, r), e);
            }
        });
        return resultList;
    }

    private OverallCoverageType calculateSimpleWeightedRangeCoverage(Geometry spatialUnitGeom,
                                                                     SimpleFeatureCollection isochronesFc,
                                                                     UUID indicatorId,
                                                                     Map<UUID, Map<LocalDate, Double>> totalIndicatorScore,
                                                                     Double range) throws OperationException {
        SimpleFeatureCollection isochronesSubsetFc = isochroneUtils.subsetRange(isochronesFc, range);
        Geometry combinedIsochroneGeometry = operationUtils.combineGeometries(isochronesSubsetFc);
        List<IndicatorCoverageValueType> overallScore;
        // Calculate intersection proportion of SpatialUnit geometry and the combined isochrone geometry
        double intersectionProportion = operationUtils.polygonalIntersectionProportion(
                spatialUnitGeom, combinedIsochroneGeometry);
        // Calculate and set overall coverage for each indicator
        overallScore = totalIndicatorScore.get(indicatorId).entrySet().stream()
                .map(e -> new IndicatorCoverageValueType()
                        .date(e.getKey())
                        .relativeCoverage((float) intersectionProportion)
                        .absoluteCoverage((float) (e.getValue() * intersectionProportion)))
                .collect(Collectors.toList());
        OverallCoverageType overallCoverage = new OverallCoverageType();
        overallCoverage.coverage(overallScore)
                .range(range.floatValue());
        return overallCoverage;
    }

    private OverallCoverageType calculateResidentialAreaWeightedRangeCoverage(Geometry spatialUnitGeom,
                                                                              SimpleFeatureCollection isochronesFc,
                                                                              UUID indicatorId,
                                                                              Map<UUID, Map<LocalDate, Double>> totalIndicatorScore,
                                                                              Double range) throws OperationException {
        try {
            SimpleFeatureCollection residentialAreaFc = featureLoader.loadFeatureCollection(dataSource);
            return calculateResidentialAreaWeightedRangeCoverage(spatialUnitGeom, isochronesFc, residentialAreaFc,
                    indicatorId, totalIndicatorScore, range);
        } catch (IOException e) {
            throw new OperationException(String.format("Could not load residential areas from data source %s", dataSource.getType()));
        }
    }

    private OverallCoverageType calculateResidentialAreaWeightedRangeCoverage(Geometry spatialUnitGeom,
                                                                              SimpleFeatureCollection isochronesFc,
                                                                              SimpleFeatureCollection residentialAreaFc,
                                                                              UUID indicatorId,
                                                                              Map<UUID, Map<LocalDate, Double>> totalIndicatorScore,
                                                                              Double range) throws OperationException {
        SimpleFeatureCollection isochronesSubsetFc = isochroneUtils.subsetRange(isochronesFc, range);
        Geometry combinedIsochroneGeometry = operationUtils.combineGeometries(isochronesSubsetFc);
        List<IndicatorCoverageValueType> overallScore;

        // Calculate intersection between the SpatiaUnit geometry and the combined isochrone Geometry
        Geometry isochroneSpatialUnitIntersectionGeom = operationUtils.polygonalIntersection(spatialUnitGeom, combinedIsochroneGeometry);
        // Use intersection geometry to preselect all residential areas that intersects it
        SimpleFeatureCollection intersectingResidentialAreasFc = operationUtils.filterIntersectingFeatures(residentialAreaFc, isochroneSpatialUnitIntersectionGeom);
        // Calculate intersection proportion of preselected residential area features and combined SpatialUnit geometry
        // by taking into account the intersection geometry above
        double intersectionProportion = operationUtils.polygonalIntersectionProportion(
                spatialUnitGeom,
                isochroneSpatialUnitIntersectionGeom,
                intersectingResidentialAreasFc
        );
        // Calculate and set overall coverage for each indicator
        overallScore = totalIndicatorScore.get(indicatorId).entrySet().stream()
                .map(e -> new IndicatorCoverageValueType()
                        .date(e.getKey())
                        .relativeCoverage((float) intersectionProportion)
                        .absoluteCoverage((float) (e.getValue() * intersectionProportion)))
                .collect(Collectors.toList());
        OverallCoverageType overallCoverage = new OverallCoverageType();
        overallCoverage.coverage(overallScore)
                .range(range.floatValue());
        return overallCoverage;
    }

    private static class IndicatorSummary {
        private Map<String, Map<UUID, List<IndicatorValue>>> lookupMap;
        private Map<UUID, Map<LocalDate, Double>> totalIndicatorScore;
    }

    private static class IndicatorValue {
        private UUID id;
        private double value;
        private LocalDate date;
    }

    private IndicatorSummary createIndicatorSummary(List<UUID> indicatorList, UUID spatialUnitId, LocalDate date) {
        Map<String, Map<UUID, List<IndicatorValue>>> lookupMap = new HashMap<>();
        Map<UUID, Map<LocalDate, Double>> totalIndicatorScoreMap = new HashMap<>();
        String dateProp = DATE_PROP_PREFIX.concat(date.toString());
        indicatorList.forEach(indicator -> {
            try {
                LOGGER.debug("Fetch indicator timeseries for SpatialUnit {} and Indicator {}", spatialUnitId, indicator);
                ArrayNode indicatorArray = dmc.getIndicatorTimeseries(indicator, spatialUnitId, authHeader);
                Iterator<JsonNode> iterator = indicatorArray.elements();
                while (iterator.hasNext()) {
                    JsonNode node = iterator.next();
                    if (node.has(ID_PROP_NAME) && node.has(dateProp)) {
                        String featureId = node.get(ID_PROP_NAME).asText();
                        IndicatorValue indicatorValue = new IndicatorValue();
                        indicatorValue.id = indicator;
                        indicatorValue.date = date;
                        indicatorValue.value = node.get(dateProp).asDouble();

                        if (lookupMap.containsKey(featureId)) {
                            Map<UUID, List<IndicatorValue>> indicatorMap = lookupMap.get(featureId);
                            if (!indicatorMap.containsKey(indicator)) {
                                List<IndicatorValue> indicatureValues = new ArrayList<>();
                                indicatorMap.put(indicator, indicatureValues);
                            }
                            indicatorMap.get(indicator).add(indicatorValue);
                        } else {
                            Map<UUID, List<IndicatorValue>> indicatorMap = new HashMap<>();
                            List<IndicatorValue> indicatureValues = new ArrayList<>();
                            indicatureValues.add(indicatorValue);
                            indicatorMap.put(indicator, indicatureValues);
                            lookupMap.put(featureId, indicatorMap);
                        }

                        if (totalIndicatorScoreMap.containsKey(indicator)) {
                            Map<LocalDate, Double> totalIndicatorDateMap = totalIndicatorScoreMap.get(indicator);
                            if (!totalIndicatorDateMap.containsKey(date)) {
                                totalIndicatorDateMap.put(date, 0.);
                            }
                            totalIndicatorDateMap.put(date, totalIndicatorDateMap.get(date) + indicatorValue.value);
                        } else {
                            Map<LocalDate, Double> totalIndicatorDateMap = new HashMap<>();
                            totalIndicatorDateMap.put(date, indicatorValue.value);
                            totalIndicatorScoreMap.put(indicator, totalIndicatorDateMap);
                        }
                    }
                }
            } catch (IOException e) {
                LOGGER.warn("Could not fetch indicator timeseries for SpatialUnit {} and Indicator {}: {}",
                        spatialUnitId, indicator, e.getMessage());
            }
        });
        IndicatorSummary summary = new IndicatorSummary();
        summary.lookupMap = lookupMap;
        summary.totalIndicatorScore = totalIndicatorScoreMap;
        return summary;
    }

    private Map<String, Map<String, Float>> calculateWeightedIntersection(SimpleFeatureCollection isochronesFc,
                                                                          SimpleFeatureCollection spatialUnitFc,
                                                                          IsochronePruneProcessType.WeightingEnum weighting) throws OperationException {
        return switch (weighting) {
            case RESIDENTIAL_AREAS -> calculateResidentialAreaWeightedIntersection(isochronesFc, spatialUnitFc);
            default -> calculateSimpleAreaWeightedIntersection(isochronesFc, spatialUnitFc);
        };
    }

    protected Map<String, Map<String, Float>> calculateSimpleAreaWeightedIntersection(SimpleFeatureCollection isochronesFc,
                                                                                      SimpleFeatureCollection spatialUnitFc) {
        Map<String, Map<String, Float>> intersectionMap = new HashMap<>();
        try (SimpleFeatureIterator iterator = isochronesFc.features()) {
            while (iterator.hasNext()) {
                SimpleFeature isochrone = iterator.next();
                String isochroneFeatureId = null;
                try {
                    isochroneFeatureId = featureUtils.getPropertyValueAsString(isochrone, ID_PROP_NAME);
                    LOGGER.debug("Calculate coverage proportions for isochrone with Feature ID {}", isochroneFeatureId);
                    // Preselect all spatial units that intersects the current isochrone
                    SimpleFeatureCollection intersectingFc = operationUtils.filterIntersectingFeatures(spatialUnitFc, isochrone);
                    Map<String, Float> spatialUnitIntersectionMap = new HashMap<>();
                    try (SimpleFeatureIterator iterator2 = intersectingFc.features()) {
                        while (iterator2.hasNext()) {
                            SimpleFeature spatialUnitFeature = iterator2.next();
                            String featureId = featureUtils.getPropertyValueAsString(spatialUnitFeature, ID_PROP_NAME);
                            double proportion = operationUtils.polygonalIntersectionProportion(spatialUnitFeature, isochrone);
                            spatialUnitIntersectionMap.put(featureId, (float) proportion);
                        }
                    }
                    intersectionMap.put(isochroneFeatureId, spatialUnitIntersectionMap);
                } catch (OperationException e) {
                    LOGGER.warn("Could not calculate area weighted intersection for isochrone {}. Cause: {}",
                            isochroneFeatureId, e.getMessage());
                }
            }
        }
        return intersectionMap;
    }

    protected Map<String, Map<String, Float>> calculateResidentialAreaWeightedIntersection(SimpleFeatureCollection isochronesFc,
                                                                                           SimpleFeatureCollection spatialUnitFc) throws OperationException {
        try {
            SimpleFeatureCollection residentialAreaFc = featureLoader.loadFeatureCollection(dataSource);
            return calculateResidentialAreaWeightedIntersection(isochronesFc, spatialUnitFc, residentialAreaFc);
        } catch (IOException e) {
            throw new OperationException(String.format("Could not load residential areas from data source %s", dataSource.getType()));
        }
    }

    protected Map<String, Map<String, Float>> calculateResidentialAreaWeightedIntersection(SimpleFeatureCollection isochronesFc,
                                                                                           SimpleFeatureCollection spatialUnitFc,
                                                                                           SimpleFeatureCollection residentialAreaFc) throws OperationException {
        Map<String, Map<String, Float>> intersectionMap = new HashMap<>();
        try (SimpleFeatureIterator iterator = isochronesFc.features()) {
            while (iterator.hasNext()) {
                SimpleFeature isochrone = iterator.next();
                String isochroneFeatureId = featureUtils.getPropertyValueAsString(isochrone, ID_PROP_NAME);
                LOGGER.debug("Calculate coverage proportions for isochrone with Feature ID {}", isochroneFeatureId);
                // Preselect all spatial units that intersects the current isochrone
                SimpleFeatureCollection intersectingSpatialUnitsFc = operationUtils.filterIntersectingFeatures(spatialUnitFc, isochrone);
                Map<String, Float> spatialUnitIntersectionMap = new HashMap<>();
                try (SimpleFeatureIterator iterator2 = intersectingSpatialUnitsFc.features()) {
                    while (iterator2.hasNext()) {
                        SimpleFeature spatialUnitFeature = iterator2.next();
                        // Calculate intersection between SpatialUnit and isochrone
                        Geometry isochroneSpatialUnitIntersectionGeom = operationUtils.polygonalIntersection(isochrone, spatialUnitFeature);
                        // Use intersection geometry to preselect all residential areas that intersects it
                        SimpleFeatureCollection intersectingResidentialAreasFc = operationUtils.filterIntersectingFeatures(residentialAreaFc, isochroneSpatialUnitIntersectionGeom);
                        // Calculate intersection proportion of preselected residential area features and the current
                        // spatial unit by taking into account the intersection geometry above
                        double proportion = operationUtils.polygonalIntersectionProportion(spatialUnitFeature, isochroneSpatialUnitIntersectionGeom, intersectingResidentialAreasFc);
                        String featureId = featureUtils.getPropertyValueAsString(spatialUnitFeature, ID_PROP_NAME);
                        spatialUnitIntersectionMap.put(featureId, (float) proportion);
                    }
                }
                intersectionMap.put(isochroneFeatureId, spatialUnitIntersectionMap);
            }
        }
        return intersectionMap;
    }
}
