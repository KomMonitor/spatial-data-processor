package org.n52.kommonitor.spatialdataprocessor.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geotools.data.geojson.GeoJSONReader;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.n52.kommonitor.spatialdataprocessor.operations.OperationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class IsochroneUtilsTest {
    private static IsochroneUtils utils;

    @BeforeAll
    static void setup() {
        utils = new IsochroneUtils();
    }

    @Test
    void testGetRanges() throws IOException, OperationException {
        InputStream input = getClass().getResourceAsStream("/isochrones.geojson");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(input);

        List<Double> ranges = utils.getRanges(node);
        Assertions.assertEquals(Arrays.asList(500.0, 1000.0, 1500.0), ranges);
    }

    @Test
    void testGetRangesThrowsExceptionForMissingNode() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree("{\"key\":\"value\"}");
        Assertions.assertThrows(OperationException.class, () -> utils.getRanges(node));
    }

    @Test
    void testGetRangesThrowsExceptionForEmptyNode() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.readTree("{\"type\":\"FeatureCollection\",\"metadata\":{\"query\":{\"range\":\"\"}}}");
        Assertions.assertThrows(OperationException.class, () -> utils.getRanges(node));
    }

    @Test
    void testSubsetRange() throws IOException {
        InputStream input = getClass().getResourceAsStream("/isochrones.geojson");
        try (GeoJSONReader reader = new GeoJSONReader(input)) {
            SimpleFeatureCollection fc = reader.getFeatures();

            SimpleFeatureCollection subset = utils.subsetRange(fc, 500.f);
            Assertions.assertEquals(7, subset.size());

            subset = utils.subsetRange(fc, 1000.f);
            Assertions.assertEquals(7, subset.size());

            subset = utils.subsetRange(fc, 1500.f);
            Assertions.assertEquals(7, subset.size());

            subset = utils.subsetRange(fc, 2000.f);
            Assertions.assertTrue(subset.isEmpty());
        }
    }
}
