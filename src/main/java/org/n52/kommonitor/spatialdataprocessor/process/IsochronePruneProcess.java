package org.n52.kommonitor.spatialdataprocessor.process;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.geotools.data.geojson.GeoJSONReader;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.n52.kommonitor.models.*;
import org.n52.kommonitor.spatialdataprocessor.operations.OperationException;
import org.n52.kommonitor.spatialdataprocessor.operations.SpatialOperationUtils;
import org.n52.kommonitor.spatialdataprocessor.util.FeatureUtils;
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

    public IsochronePruneProcess(IsochronePruneProcessType parameters, DataManagementClient dmc,
                                 SpatialOperationUtils operationUtils, FeatureUtils featureUtils) {
        this.parameters = parameters;
        this.dmc = dmc;
        this.operationUtils = operationUtils;
        this.featureUtils = featureUtils;
    }


    @Override
    public Object execute() throws Exception {
        List<UUID> indicatorList = parameters.getIndicator();
        UUID spatialUnitId = parameters.getSpatialUnit();
        String isochrones = (String) parameters.getIsochron();
        LocalDate date = parameters.getDate();

        // 1) Fetch geometries only for the specified SpatialUnit and create a FeatureCollection for it as well
        // as for the provided isochrones
        LOGGER.debug("Fetch geometries for SpatialUnit {}", spatialUnitId);
        ObjectNode spatialUnit = dmc.getSpatialUnitGeoJSON(spatialUnitId);
        SimpleFeatureCollection spatialUnitFc = GeoJSONReader.parseFeatureCollection(spatialUnit.toString());
        SimpleFeatureCollection isochronesFc = GeoJSONReader.parseFeatureCollection(isochrones);

        return calculateIsochronePrune(indicatorList, spatialUnitId, date, isochronesFc, spatialUnitFc);
    }

    protected List<IsochronePruneProcessResultType> calculateIsochronePrune(List<UUID> indicatorList,
                                                                            UUID spatialUnitId,
                                                                            LocalDate date,
                                                                            SimpleFeatureCollection isochronesFc,
                                                                            SimpleFeatureCollection spatialUnitFc) throws OperationException {
        // 2) Fetch indicators with timeseries only without geometry and store it within a lookup HashMap by indexing
        // it with the SpatialUnit Feature IDs
        IndicatorSummary createIndicatorSummary = createIndicatorSummary(indicatorList, spatialUnitId, date);
        Map<String, Map<UUID, List<IndicatorValue>>> lookupMap = createIndicatorSummary.lookupMap;
        Map<UUID, Map<LocalDate, Double>> totalIndicatorScore = createIndicatorSummary.totalIndicatorScore;

        // 3) Calculate intersections between POIs (isochrones) and SpatialUnit features
        Map<String, Map<String, Float>> intersectionMap = createIntersectionMap(isochronesFc, spatialUnitFc);

        // 4) Calculate indicator coverages for each combination of isochrones and SpatialUnits that intersect
        List<IsochronePruneProcessResultType> resultList = new ArrayList<>();
        indicatorList.forEach(i -> {
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
            Geometry combinedIsochroneGemetry = operationUtils.combineGeometries(isochronesFc);
            Geometry combinedSpatialUnitGeometry = operationUtils.combineGeometries(spatialUnitFc);
            List<IndicatorCoverageValueType> overallScore = null;
            try {
                overallScore = calculateOverallScore(spatialUnitFc, isochronesFc, totalIndicatorScore);
                result.setOverallCoverage(overallScore.get(0));
                resultList.add(result);
            } catch (OperationException e) {
                LOGGER.error("Could not calculate overall indicator coverage for indicator {} due to error: {}", i, e.getMessage());
            }
        });

        return resultList;
    }

    private List<IndicatorCoverageValueType> calculateOverallScore(SimpleFeatureCollection spatialUnitFc,
                                                                   SimpleFeatureCollection isochronesFc,
                                                                   UUID indicatorId,
                                                                   Map<UUID, Map<LocalDate, Double>> totalIndicatorScore) throws OperationException {
        Geometry combinedIsochroneGemetry = operationUtils.combineGeometries(isochronesFc);
        Geometry combinedSpatialUnitGeometry = operationUtils.combineGeometries(spatialUnitFc);
        List<IndicatorCoverageValueType> overallScore = null;

        double intersectionProportion = operationUtils.polygonalIntersectionProportion(
                combinedSpatialUnitGeometry, combinedIsochroneGemetry);
        overallScore = totalIndicatorScore.get(indicatorId).entrySet().stream()
                .map(e -> new IndicatorCoverageValueType()
                        .date(e.getKey())
                        .relativeCoverage((float) intersectionProportion)
                        .absoluteCoverage((float) (e.getValue() * intersectionProportion)))
                .collect(Collectors.toList());
        return overallScore;
    }

    private class IndicatorSummary {
        private Map<String, Map<UUID, List<IndicatorValue>>> lookupMap;
        private Map<UUID, Map<LocalDate, Double>> totalIndicatorScore;
    }

    private class IndicatorValue {
        private UUID id;
        private double value;
        private LocalDate date;
    }

    private IndicatorSummary createIndicatorSummary(List<UUID> indicatorList, UUID spatialUnitId, LocalDate date) {
        Map<String, Map<UUID, List<IndicatorValue>>> lookupMap = new HashMap();
        Map<UUID, Map<LocalDate, Double>> totalIndicatorScoreMap = new HashMap<>();
        String dateProp = DATE_PROP_PREFIX.concat(date.toString());
        indicatorList.forEach(indicator -> {
            try {
                LOGGER.debug("Fetch indicator timeseries for SpatialUnit {} and Indicator {}", spatialUnitId, indicator);
                ArrayNode indicatorArray = dmc.getIndicatorTimeseries(indicator, spatialUnitId);
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

    private Map<String, Map<String, Float>> createIntersectionMap(SimpleFeatureCollection isochronesFc, SimpleFeatureCollection spatialUnitFc) throws OperationException {
        Map<String, Map<String, Float>> intersectionMap = new HashMap<>();

        try (SimpleFeatureIterator iterator = isochronesFc.features()) {
            while (iterator.hasNext()) {
                SimpleFeature isochrone = iterator.next();
                String isochroneFeatureId = featureUtils.getPropertyValueAsString(isochrone, ID_PROP_NAME);
                LOGGER.debug("Calculate coverage proportions for isochrone with Feature ID {}", isochroneFeatureId);
                // Preselect all spatial units that intersects the current isochrone
                SimpleFeatureCollection intersectingFc = operationUtils.selectIntersectingFeatures(spatialUnitFc, isochrone);
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
            }
        }
        return intersectionMap;
    }
}
