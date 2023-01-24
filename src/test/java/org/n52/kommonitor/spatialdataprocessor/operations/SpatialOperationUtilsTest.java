package org.n52.kommonitor.spatialdataprocessor.operations;

import org.geotools.data.geojson.GeoJSONReader;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.mockito.Mockito;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SpatialOperationUtilsTest {

    private static SpatialOperationUtils utils;
    private static final String DEFAULT_EPSG = "EPSG:4326";

    @BeforeAll
    static void setup(){
        utils = new SpatialOperationUtils();
    }

    @Test
    public void polygonalIntersectionTest() throws ParseException, OperationException, FactoryException {
        SimpleFeature sf1 = mockSimpleFeature("POLYGON((0 0, 20 0, 20 20, 0 20, 0 0))", DEFAULT_EPSG);
        SimpleFeature sf2 = mockSimpleFeature("POLYGON((10 10, 30 10, 30 30, 10 30, 10 10))", DEFAULT_EPSG);

        Polygon res = (Polygon) utils.polygonalIntersection(sf1, sf2);

        Assertions.assertFalse(res.isEmpty());
    }

    @Test
    public void polygonalEmptyIntersectionTest() throws ParseException, OperationException, FactoryException {
        SimpleFeature sf1 = mockSimpleFeature("POLYGON((0 0, 20 0, 20 20, 0 20, 0 0))", DEFAULT_EPSG);
        SimpleFeature sf2 = mockSimpleFeature("POLYGON((30 30, 50 30, 50 50, 30 50, 30 30))", DEFAULT_EPSG);

        Polygon res = (Polygon) utils.polygonalIntersection(sf1, sf2);

        Assertions.assertTrue(res.isEmpty());
    }

    @Test
    public void polygonalIntersectionThrowsExceptionForNonPolygonTest() throws ParseException, OperationException, FactoryException {
        SimpleFeature sf1 = mockSimpleFeature("POLYGON((0 0, 20 0, 20 20, 0 20, 0 0))", DEFAULT_EPSG);
        SimpleFeature sf2 = mockSimpleFeature("POINT(10 10)", DEFAULT_EPSG);

        Assertions.assertThrows(OperationException.class, () ->utils.polygonalIntersection(sf1, sf2));
    }

    @Test
    public void polygonalIntersectionThrowsExceptionForDifferentCrsTest() throws ParseException, OperationException, FactoryException {
        SimpleFeature sf1 = mockSimpleFeature("POLYGON((0 0, 20 0, 20 20, 0 20, 0 0))", DEFAULT_EPSG);
        SimpleFeature sf2 = mockSimpleFeature("POINT(10 10)", "EPSG:31467");

        Assertions.assertThrows(OperationException.class, () ->utils.polygonalIntersection(sf1, sf2));
    }

    @Test
    public void polygonalIntersectionProportionTest() throws ParseException, OperationException, FactoryException {
        SimpleFeature sf1 = mockSimpleFeature("POLYGON((0 0, 20 0, 20 20, 0 20, 0 0))", DEFAULT_EPSG);
        SimpleFeature sf2 = mockSimpleFeature("POLYGON((10 10, 30 10, 30 30, 10 30, 10 10))", DEFAULT_EPSG);
        SimpleFeature sf3 = mockSimpleFeature("POLYGON((30 30, 50 30, 50 50, 30 50, 30 30))", DEFAULT_EPSG);
        SimpleFeature sf4 = mockSimpleFeature("POLYGON EMPTY", DEFAULT_EPSG);

        Double res = utils.polygonalIntersectionProportion(sf1, sf2);

        Assertions.assertEquals(0.25, utils.polygonalIntersectionProportion(sf1, sf2));
        Assertions.assertEquals(0, utils.polygonalIntersectionProportion(sf1, sf3));
        Assertions.assertEquals(1, utils.polygonalIntersectionProportion(sf1, sf1));
        Assertions.assertEquals(0, utils.polygonalIntersectionProportion(sf4, sf1));
    }

    @Test
    public void polygonalIntersectionProportionFeatureCollectionTest() throws ParseException, OperationException, FactoryException {
        SimpleFeature sf1 = mockSimpleFeature("POLYGON((0 0, 20 0, 20 20, 0 20, 0 0))", DEFAULT_EPSG);
        SimpleFeature sf2 = mockSimpleFeature("POLYGON((5 5, 10 5, 10 15, 5 15, 5 5))", DEFAULT_EPSG);
        SimpleFeature sf3 = mockSimpleFeature("POLYGON((15 5, 20 5, 20 10, 15 10, 15 5))", DEFAULT_EPSG);
        SimpleFeature sf4 = mockSimpleFeature("POLYGON((15 15, 20 15, 20 20, 15 20, 15 15))", DEFAULT_EPSG);
        SimpleFeature sf5 = mockSimpleFeature("POLYGON((30 30, 50 30, 50 50, 30 50, 30 30))", DEFAULT_EPSG);

        SimpleFeatureIterator iterator = Mockito.mock(SimpleFeatureIterator.class);
        Mockito.when(iterator.hasNext()).thenReturn(true, true, true, true, false);
        Mockito.when(iterator.next()).thenReturn(sf2, sf3, sf4, sf5);

        SimpleFeatureCollection fc = Mockito.mock(SimpleFeatureCollection.class);
        Mockito.when(fc.features()).thenReturn(iterator);

        Assertions.assertEquals(0.25, utils.polygonalIntersectionProportion(sf1, fc));
    }

    @Test
    public void filterIntersectingFeaturesTest() throws IOException, OperationException {
        SimpleFeatureCollection fc = readTestFeatureCollectionGeoJsonFile("/featureCollectionTest.geojson");
        SimpleFeature f = readTestFeatureGeoJsonFile("/featureTest.geojson");

        SimpleFeatureCollection subFc = utils.filterIntersectingFeatures(fc, f);
        Assertions.assertFalse(subFc.isEmpty());
        Assertions.assertEquals(3, subFc.size());
    }

    @Test
    public void filterIntersectingFeaturesWithGeometryTest() throws IOException, OperationException {
        SimpleFeatureCollection fc = readTestFeatureCollectionGeoJsonFile("/featureCollectionTest.geojson");
        SimpleFeature f = readTestFeatureGeoJsonFile("/featureTest.geojson");

        SimpleFeatureCollection subFc = utils.filterIntersectingFeatures(fc, (Geometry) f.getDefaultGeometry());
        Assertions.assertFalse(subFc.isEmpty());
        Assertions.assertEquals(3, subFc.size());
    }

    private SimpleFeature mockSimpleFeature(String wkt, String epsg) throws ParseException, FactoryException {
        GeometryFactory geomFactory = JTSFactoryFinder.getGeometryFactory();

        WKTReader reader = new WKTReader(geomFactory);
        Geometry geom = (Geometry) reader.read(wkt);

        SimpleFeature feature = Mockito.mock(SimpleFeature.class);
        SimpleFeatureType sfType = Mockito.mock(SimpleFeatureType.class);

        Mockito.when(feature.getDefaultGeometry()).thenReturn(geom);
        Mockito.when(sfType.getCoordinateReferenceSystem()).thenReturn(CRS.decode(epsg));
        Mockito.when(feature.getFeatureType()).thenReturn(sfType);

        return feature;
    }

    private SimpleFeatureCollection mockSimpleFeatureCollection(SimpleFeatureIterator iterator, String epsg) throws FactoryException {
        SimpleFeatureType sfType = Mockito.mock(SimpleFeatureType.class);
        Mockito.when(sfType.getCoordinateReferenceSystem()).thenReturn(CRS.decode(epsg));

        SimpleFeatureCollection fc = Mockito.mock(SimpleFeatureCollection.class);
        Mockito.when(fc.features()).thenReturn(iterator);
        Mockito.when(fc.getSchema()).thenReturn(sfType);
        return fc;
    }

    private SimpleFeatureCollection readTestFeatureCollectionGeoJsonFile(String path) throws IOException {
        InputStream input = getClass().getResourceAsStream(path);
        try (GeoJSONReader reader = new GeoJSONReader(input)) {
            SimpleFeatureCollection fc = reader.getFeatures();
            return fc;
        }
    }

    private SimpleFeature readTestFeatureGeoJsonFile(String path) throws IOException {
        InputStream input = getClass().getResourceAsStream(path);
        try (GeoJSONReader reader = new GeoJSONReader(input)) {
            SimpleFeature f = reader.getFeature();
            return f;
        }
    }
}
