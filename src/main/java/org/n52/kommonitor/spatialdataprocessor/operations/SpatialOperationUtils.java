package org.n52.kommonitor.spatialdataprocessor.operations;

import org.geotools.data.crs.ReprojectFeatureReader;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.springframework.stereotype.Component;

@Component
public class SpatialOperationUtils {

    /***
     * Calculates the polygonal intersection Geometry of the geometries from two SimpleFeatures, assuming that both
     * SimpleFeatures have the same CoordinateReferenceSystem
     *
     * @param sf1 First SimpleFeature
     * @param sf2 Second SimpleFeature
     * @return Intersecting Geometry
     * @throws OperationException if one of the two SimpleFeature geometries is not a Polygon or MultiPolygon and
     * CRS are different but transformCrs is false.
     */
    public Geometry polygonalIntersection(SimpleFeature sf1, SimpleFeature sf2) throws OperationException {
        if (!checkCrs(sf1, sf2)) {
            throw new OperationException("Can not calculate intersection for two SimpleFeatures that have different" +
                    " CoordinateReferenceSystems.");
        }
        return polygonalIntersection(
                (Geometry) sf1.getDefaultGeometry(),
                (Geometry) sf2.getDefaultGeometry()
        );
    }

    /**
     * Calculates the polygonal intersection Geometry of two geometries
     *
     * @param geom1 First Geometry
     * @param geom2 Second Geometry
     * @return Intersecting Geometry
     * @throws OperationException if one of the two geometries is not a Polygon or MultiPolygon
     */
    public Geometry polygonalIntersection(Geometry geom1, Geometry geom2) throws OperationException {
        checkPolygonalType(geom1);
        checkPolygonalType(geom2);
        return geom1.intersection(geom2);
    }

    /***
     * Checks a Geometry for types {@link Polygon} and {@link MultiPolygon}. If the Geometry is instance of another
     * type, this method throws an Exception.
     *
     * @param geom Geometry to check
     * @throws OperationException
     */
    private void checkPolygonalType(Geometry geom) throws OperationException {
        if (!(geom instanceof Polygon) && !(geom instanceof MultiPolygon)) {
            throw new OperationException("Geometry is not of one of the types:" + "[Polygon, MultiPolygon].");
        }
    }

    private boolean checkCrs(SimpleFeature sf1, SimpleFeature sf2) {
        CoordinateReferenceSystem crs1 = sf1.getFeatureType().getCoordinateReferenceSystem();
        CoordinateReferenceSystem crs2 = sf2.getFeatureType().getCoordinateReferenceSystem();
        return crs1.getName().equals(crs2.getName());
    }

}
