package org.n52.kommonitor.dataloader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.geotools.api.data.DataStore;
import org.geotools.api.data.FeatureSource;
import org.geotools.api.data.FileDataStoreFinder;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.geotools.api.filter.Filter;
import org.geotools.api.filter.FilterFactory;
import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.factory.CommonFactoryFinder;
import org.springframework.stereotype.Component;

@Component
public class ShapeFileLoader implements FeatureLoader<ShapeFileDataSource>{

    @Override
    public String getSupportedDataSource() {
        return ShapeFileDataSource.DATA_SOURCE_TYPE;
    }

    @Override
    public SimpleFeatureCollection loadFeatureCollection(ShapeFileDataSource dataSource) throws IOException {
        File file = new File(dataSource.getFilePath());
        DataStore dataStore = FileDataStoreFinder.getDataStore(file);
        String typeName = dataStore.getTypeNames()[0];
        FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        FilterFactory ff = CommonFactoryFinder.getFilterFactory();
        List<Filter> filterList = dataSource.getFieldValues()
                .stream()
                .map(v -> ff.equals(ff.property(dataSource.getFieldName()), ff.literal(v)))
                .collect(Collectors.toList());

        Filter filter = ff.or(filterList);
        return DataUtilities.simple(source.getFeatures(filter));
    }
}
