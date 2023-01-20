package org.n52.kommonitor.dataloader;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureCollection;

import java.io.IOException;

/**
 * Interface for loading Features from a certain datasource, such as Shapefiles or GeoJSON.
 */
public interface FeatureLoader<T extends FeatureDataSource> {
    String getSupportedDataSource();

    FeatureCollection loadFeatureCollection(T dataSource) throws IOException;
}
