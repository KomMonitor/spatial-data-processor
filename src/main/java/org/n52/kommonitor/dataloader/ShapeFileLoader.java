package org.n52.kommonitor.dataloader;

import org.geotools.data.*;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.factory.CommonFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShapeFileLoader implements FeatureLoader<ShapeFileDataSource>{

    @Override
    public String getSupportedDataSource() {
        return ShapeFileDataSource.DATA_SOURCE_TYPE;
    }

    @Override
    public SimpleFeatureCollection loadFeatureCollection(ShapeFileDataSource dataSource) throws IOException {
        File file = new File(dataSource.filePath());
        DataStore dataStore = FileDataStoreFinder.getDataStore(file);
        String typeName = dataStore.getTypeNames()[0];
        FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
        List<Filter> filterList = dataSource.fieldValues()
                .stream()
                .map(v -> ff.equals(ff.property(dataSource.fieldName()), ff.literal(v)))
                .collect(Collectors.toList());

        Filter filter = ff.or(filterList);
        return DataUtilities.simple(source.getFeatures(filter));
    }
}
