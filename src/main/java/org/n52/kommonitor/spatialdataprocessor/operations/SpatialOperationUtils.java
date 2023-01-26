package org.n52.kommonitor.spatialdataprocessor.operations;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.*;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.type.FeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.geometry.BoundingBox;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpatialOperationUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpatialOperationUtils.class);

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

    /**
     * Calculates the proportion of intersection for the geometries of two SimpleFeatures. The result gives the
     * proportion of the intersecting geometry in relation to the geometry of the first SimpleFeature.
     *
     * @param sf1 First SimpleFeature for which the proportion of intersection will be calculated
     * @param sf2 Second SimpleFeature
     * @return Proportion of intersection from 0 (no intersection) to 1 (full overlap).
     * @throws OperationException if one of the two geometries is not a Polygon or MultiPolygon
     */
    public double polygonalIntersectionProportion(SimpleFeature sf1, SimpleFeature sf2) throws OperationException {
        Geometry geom = polygonalIntersection(sf1, sf2);
        // This is fail-safe for an empty geometry of the first SimpleFeature, since it prevents dividing by zero area.
        if (geom.isEmpty()) {
            return 0;
        } else {
            return geom.getArea() / ((Polygon) sf1.getDefaultGeometry()).getArea();
        }
    }

    public double polygonalIntersectionProportion(Geometry geom1, Geometry geom2) throws OperationException {
        Geometry geom = polygonalIntersection(geom1, geom2);
        // This is fail-safe for an empty geometry of the first SimpleFeature, since it prevents dividing by zero area.
        if (geom.isEmpty()) {
            return 0;
        } else {
            return geom.getArea() / geom1.getArea();
        }
    }

    /**
     * Calculates the proportion of intersection of two Geometries, by using a subset of the first Geometry.
     *
     * The subset of the first Geometry will be used to calculate the intersection with the second Geometry. This new
     * intersection then is used to calculate the proportion of intersection..
     *
     * @param geom1 First Geometry for which the proportion of intersection will be calculated
     * @param geom2 Second Geometry
     * @param subsetGeometry Subset og the first Geometry, which will be used to calculate and intersection with the
     *                       second Geometry
     * @return Proportion of intersection from 0 (no intersection) to 1 (full overlap).
     * @throws OperationException if one of the two geometries is not a Polygon or MultiPolygon
     */
    public double polygonalIntersectionProportion(Geometry geom1, Geometry geom2, Geometry subsetGeometry) throws OperationException {
        Geometry geom = polygonalIntersection(geom2, subsetGeometry);
        // This is fail-safe for an empty geometry of the first SimpleFeature, since it prevents dividing by zero area.
        if (geom.isEmpty()) {
            return 0;
        } else {
            return geom.getArea() / geom1.getArea();
        }
    }

    public double polygonalIntersectionProportion(SimpleFeature feature, SimpleFeatureCollection featureCollection) {
        double intersection = 0;
        try (SimpleFeatureIterator iterator = featureCollection.features()) {
            while (iterator.hasNext()) {
                SimpleFeature f2 = iterator.next();
                try {
                    intersection += polygonalIntersectionProportion(feature, f2);
                } catch (OperationException e) {
                    LOGGER.warn("Could not calculate intersection between features {} and {}", feature.getID(), f2.getID());
                    throw new RuntimeException(e);
                }
            }
        }
        return intersection;
    }


    public double polygonalIntersectionProportion(Geometry geom1, Geometry geom2, SimpleFeatureCollection featureCollection) {
        double intersection = 0;
        try (SimpleFeatureIterator iterator = featureCollection.features()) {
            while (iterator.hasNext()) {
                SimpleFeature f = iterator.next();
                try {
                    intersection += polygonalIntersectionProportion(geom1, geom2, (Geometry)f.getDefaultGeometry());
                } catch (OperationException e) {
                    LOGGER.warn("Could not calculate intersection between Geometry and feature {}", f.getID());
                    throw new RuntimeException(e);
                }
            }
        }
        return intersection;
    }


    public double polygonalIntersectionProportion(SimpleFeature feature, Geometry geom, SimpleFeatureCollection featureCollection) {
        return polygonalIntersectionProportion((Geometry) feature.getDefaultGeometry(), geom, featureCollection);
    }

    public SimpleFeatureCollection filterIntersectingFeatures(SimpleFeatureCollection fc, SimpleFeature feature)
            throws OperationException {
        FeatureType schema = fc.getSchema();
        if (!checkCrs(schema, feature.getFeatureType())) {
            throw new OperationException("Can not calculate intersection for two SimpleFeatures that have different" +
                    " CoordinateReferenceSystems.");
        }

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
        String geomName = schema.getGeometryDescriptor().getLocalName();
        BoundingBox bounds = feature.getBounds();

        Filter filter1 = ff.bbox(ff.property(geomName), bounds);
        Filter filter2 = ff.intersects(ff.property(geomName), ff.literal(feature.getDefaultGeometry()));
        Filter filter = ff.and(filter1, filter2);

        return fc.subCollection(filter);
    }

    /**
     * Filters all SimpleFeatures of a SimpleFeatureCollection that intersects a given Geometry.
     *
     * Note, that Geometry must be in the same CRS as the SimpleFeatureCollection. Since Geometry is CRS-less,
     * a {@link ReferencedEnvelope} is created from the {@link Envelope} of the given Geometry and the CRS of the
     * SimpleFeatureCollection.
     *
     * @param fc SimpleFeatureCollection to filter SimpleFeature from
     * @param geometry Geometry used for the intersection filter
     * @return Subset SimpleFeatureCollection that only contains SimpleFeatures that intersect the given Geometry
     */
    public SimpleFeatureCollection filterIntersectingFeatures(SimpleFeatureCollection fc, Geometry geometry)
            throws OperationException {
        FeatureType schema = fc.getSchema();

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
        String geomName = schema.getGeometryDescriptor().getLocalName();
        Envelope env = geometry.getEnvelopeInternal();
        ReferencedEnvelope bbox = new ReferencedEnvelope(env.getMinX(), env.getMinY(), env.getMaxX(), env.getMaxY(),
                schema.getCoordinateReferenceSystem());

        Filter filter1 = ff.bbox(ff.property(geomName), bbox);
        Filter filter2 = ff.intersects(ff.property(geomName), ff.literal(geometry));
        Filter filter = ff.and(filter1, filter2);

        return fc.subCollection(filter);
    }

    public Geometry combineGeometries(SimpleFeatureCollection fc) {
        List<Geometry> geometryList = new ArrayList<>();
        try (SimpleFeatureIterator iterator = fc.features()) {
            while (iterator.hasNext()) {
                SimpleFeature feature = iterator.next();
                geometryList.add((Geometry) feature.getDefaultGeometry());
                feature.getAttributeCount();
            }
        }
        return combineGeometries(geometryList);
    }

    public Geometry combineGeometries(List<Geometry> geometries) {
        GeometryFactory geoFac = new GeometryFactory();
        GeometryCollection geometryCollection = (GeometryCollection) geoFac.buildGeometry(geometries);
        return geometryCollection.union();
    }

    /***
     * Checks a Geometry for types {@link Polygon} and {@link MultiPolygon}. If the Geometry is instance of another
     * type, this method throws an Exception.
     *
     * @param geom Geometry to check
     * @throws OperationException
     */
    public void checkPolygonalType(Geometry geom) throws OperationException {
        if (!(geom instanceof Polygon) && !(geom instanceof MultiPolygon)) {
            throw new OperationException("Geometry is not of one of the types:" + "[Polygon, MultiPolygon].");
        }
    }

    /**
     * Checks if the CRS of two SimpleFeatures are identical.
     *
     * @param sf1 First SimpleFeature
     * @param sf2 Second SimpleFeature
     * @return True if the CRS of both SimpleFeatures is identical
     */
    public boolean checkCrs(SimpleFeature sf1, SimpleFeature sf2) {
        return checkCrs(sf1.getFeatureType(), sf2.getFeatureType());
    }

    /**
     * Checks if the CRS of two SimpleFeatureCollections is identical
     *
     * @param fc1 First SimpleFeatureCollection
     * @param fc2 Seconds SimpleFeatureCollection
     * @return True if the CRS of both SimpleFeatureCollections is identical
     */
    public boolean checkCrs(SimpleFeatureCollection fc1, SimpleFeatureCollection fc2) {
        return checkCrs(fc1.getSchema(), fc2.getSchema());
    }

    private boolean checkCrs(FeatureType schema1, FeatureType schema2) {
        CoordinateReferenceSystem crs1 = schema1.getCoordinateReferenceSystem();
        CoordinateReferenceSystem crs2 = schema2.getCoordinateReferenceSystem();
        return CRS.equalsIgnoreMetadata(crs1, crs2);
    }

}
