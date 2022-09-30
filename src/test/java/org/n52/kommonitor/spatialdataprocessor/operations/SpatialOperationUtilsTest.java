package org.n52.kommonitor.spatialdataprocessor.operations;

import org.geotools.geometry.jts.JTSFactoryFinder;
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

public class SpatialOperationUtilsTest {

    private static SpatialOperationUtils utils;

    @BeforeAll
    static void setup(){
        utils = new SpatialOperationUtils();
    }

    @Test
    public void polygonalIntersectionTest() throws ParseException, OperationException {
        SimpleFeature sf1 = mockSimpleFeature("POLYGON((0 0, 20 0, 20 20, 0 20, 0 0))");
        SimpleFeature sf2 = mockSimpleFeature("POLYGON((10 10, 30 10, 30 30, 10 30, 10 10))");

        Polygon res = (Polygon) utils.polygonalIntersection(sf1, sf2);

        Assertions.assertFalse(res.isEmpty());
    }

    @Test
    public void polygonalEmptyIntersectionTest() throws ParseException, OperationException {
        SimpleFeature sf1 = mockSimpleFeature("POLYGON((0 0, 20 0, 20 20, 0 20, 0 0))");
        SimpleFeature sf2 = mockSimpleFeature("POLYGON((30 30, 50 30, 50 50, 30 50, 30 30))");

        Polygon res = (Polygon) utils.polygonalIntersection(sf1, sf2);

        Assertions.assertTrue(res.isEmpty());
    }

    @Test
    public void polygonalIntersectionThrowsExceptionForNonPolygonTest() throws ParseException, OperationException {
        SimpleFeature sf1 = mockSimpleFeature("POLYGON((0 0, 20 0, 20 20, 0 20, 0 0))");
        SimpleFeature sf2 = mockSimpleFeature("POINT(10 10)");

        Assertions.assertThrows(OperationException.class, () ->utils.polygonalIntersection(sf1, sf2));
    }

    private SimpleFeature mockSimpleFeature(String wkt) throws ParseException {
        GeometryFactory geomFactory = JTSFactoryFinder.getGeometryFactory();

        WKTReader reader = new WKTReader(geomFactory);
        Geometry geom = (Geometry) reader.read(wkt);

        SimpleFeature feature = Mockito.mock(SimpleFeature.class);
        Mockito.when(feature.getDefaultGeometry()).thenReturn(geom);
        return feature;
    }
}
