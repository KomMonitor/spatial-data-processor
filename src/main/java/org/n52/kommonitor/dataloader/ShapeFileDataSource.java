package org.n52.kommonitor.dataloader;

import java.util.List;

public record ShapeFileDataSource(String filePath, String fieldName,
                                  List<String> fieldValues) implements FeatureDataSource {
    public static final String DATA_SOURCE_TYPE = "esri_shapefile";

    @Override
    public String getType() {
        return DATA_SOURCE_TYPE;
    }
}
