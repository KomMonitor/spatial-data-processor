package org.n52.kommonitor.spatialdataprocessor.process;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.geotools.data.geojson.GeoJSONReader;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.n52.kommonitor.models.IndicatorCoverageType;
import org.n52.kommonitor.models.IndicatorCoverageValueType;
import org.n52.kommonitor.models.IsochronePruneProcessResultType;
import org.n52.kommonitor.models.IsochronePruneProcessType;
import org.n52.kommonitor.spatialdataprocessor.operations.OperationException;
import org.n52.kommonitor.spatialdataprocessor.operations.SpatialOperationUtils;
import org.n52.kommonitor.spatialdataprocessor.util.FeatureUtils;
import org.n52.kommonitor.spatialdataprocessor.util.datamanagement.DataManagementClient;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

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

        // 2) Fetch indicators as timeseries only without geometry and store it within a lookup HashMap by indexing
        // it with the SpatialUnit Feature IDs
        Map<String, List<IndicatorValue>> lookupMap = createLookupMap(indicatorList, spatialUnitId, date);
        List<IndicatorCoverageType> spatialUnitCoverageList = new ArrayList<>();
        Map<UUID, IsochronePruneProcessResultType> resultMap = new HashMap<>();

        // 3) For each isochrone, determine intersecting SpatialUnits
        try (SimpleFeatureIterator iterator = isochronesFc.features()) {
            while (iterator.hasNext()) {
                SimpleFeature isochrone = iterator.next();
                String isochroneFeatureId = featureUtils.getPropertyValueAsString(isochrone, ID_PROP_NAME);
                LOGGER.debug("Calculate coverage proportions for isochrone with Feature ID {}", isochroneFeatureId);
                // Preselect all spatial units that intersects the current isochrone
                SimpleFeatureCollection intersectingFc = operationUtils.selectIntersectingFeatures(spatialUnitFc, isochrone);
                // Map to store the summed up coverage for the current isochrone
                Map<UUID, Float> poiCoverageSumMap = new HashMap<>();

                // 4) Iterate over all intersecting feature, to determine the intersection proportion between
                // SpatialUnits and the current isochrone
                try (SimpleFeatureIterator iterator2 = intersectingFc.features()) {
                    while (iterator2.hasNext()) {

                        SimpleFeature spatialUnitFeature = iterator2.next();
                        String featureId = featureUtils.getPropertyValueAsString(spatialUnitFeature, ID_PROP_NAME);
                        LOGGER.debug("Calculate coverage proportions for isochrone {} and SpatialUnit {}",
                                isochroneFeatureId, featureId);
                        double proportion = operationUtils.polygonalIntersectionProportion(spatialUnitFeature, isochrone);

                        // 5) Calculate indicator value fraction by using the intersecting proportion
                        if (lookupMap.containsKey(featureId)) {
                            List<IndicatorValue> indicatorValues = lookupMap.get(featureId);
                            for (IndicatorValue v : indicatorValues) {
                                if (!resultMap.containsKey(v.id)) {
                                    IsochronePruneProcessResultType result = new IsochronePruneProcessResultType()
                                            .indicatorId(v.id)
                                            .spatialUnitCoverage(new ArrayList<IndicatorCoverageType>());
                                    resultMap.put(v.id, result);
                                }
                                // Calculate the single SpatialUnit coverage for the current isochrone
                                float absoluteCoverage = (float) (v.value * proportion);

                                IndicatorCoverageType spatialUnitCoverage = new IndicatorCoverageType();
                                spatialUnitCoverage.setPoiFeatureId(isochroneFeatureId);
                                spatialUnitCoverage.setSpatialUnitFeatureId(featureId);
                                spatialUnitCoverage.setCoverage(Arrays.asList(
                                                new IndicatorCoverageValueType()
                                                        .relativeCoverage((float) proportion)
                                                        .absoluteCoverage(absoluteCoverage)
                                                        .date(v.date)
                                        )
                                );
                                resultMap.get(v.id).getSpatialUnitCoverage().add(spatialUnitCoverage);
                                // Sum up overall indicator value fractions for each isochrone. This can be used
                                // later for overall indicator coverage calculations.
                                if (poiCoverageSumMap.containsKey(v.id)) {
                                    float coverageSum = poiCoverageSumMap.get(v.id) + absoluteCoverage;
                                    poiCoverageSumMap.put(v.id, coverageSum);
                                } else {
                                    poiCoverageSumMap.put(v.id, absoluteCoverage);
                                }
                            }
                        }
                    }
                }
                // TODO calculate summed up indicator coverage for each isochron
            }
            // TODO calculate summed up overall indicator coverage all isochrones
        }
        return resultMap.values();
    }

    private class IndicatorValue {
        private UUID id;
        private double value;
        private LocalDate date;
    }

    private Map createLookupMap(List<UUID> indicatorList, UUID spatialUnitId, LocalDate date) {
        Map<String, List<IndicatorValue>> lookupMap = new HashMap();
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
                            lookupMap.get(featureId).add(indicatorValue);
                        } else {
                            lookupMap.put(featureId, new ArrayList<>(List.of(indicatorValue)));
                        }
                    }
                }
            } catch (IOException e) {
                LOGGER.warn("Could not fetch indicator timeseries for SpatialUnit {} and Indicator {}: {}",
                        spatialUnitId, indicator, e.getMessage());
            }
        });
        return lookupMap;
    }
}
