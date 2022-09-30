package org.n52.kommonitor.spatialdataprocessor.operations;

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
import org.opengis.referencing.crs.CoordinateReferenceSystem;

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
}
