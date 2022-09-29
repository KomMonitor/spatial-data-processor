package org.n52.kommonitor.spatialdataprocessor.operations;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.stereotype.Component;

@Component
public class SpatialOperationUtils {

    /***
     * * Calculates the polygonal intersection Geometry of the geometries from two SimpleFeatures
     *
     * @param feature1 First SimpleFeature
     * @param feature2 Second SimpleFeature
     * @return Intersecting Geometry
     * @throws OperationException if one of the two SimpleFeature geometries is not a Polygon or MultiPolygon
     */
    public Geometry polygonalIntersection(SimpleFeature feature1, SimpleFeature feature2) throws OperationException{
        //TODO Add check for same projection.
        //TODO Add CRS transformation.
        return polygonalIntersection(
                (Geometry)feature1.getDefaultGeometry(),
                (Geometry)feature2.getDefaultGeometry()
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
    public Geometry polygonalIntersection(Geometry geom1, Geometry geom2) throws OperationException{
        //TODO Handling of empty Geometry
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
    private void checkPolygonalType(Geometry geom) throws OperationException{
        if (!(geom instanceof Polygon) && !(geom instanceof MultiPolygon)){
            throw new OperationException("Geometry is not of one of the types:" + "[Polygon, MultiPolygon].");
        }
    }
}
