package org.n52.kommonitor.dataloader;

import java.util.List;

public class ShapeFileDataSource implements FeatureDataSource {
    public static final String DATA_SOURCE_TYPE = "ESRI Shapefile";

    private String filePath;
    private String fieldName;
    private List<String> fieldValues;

    public ShapeFileDataSource(String filePath, String fieldName, List<String> fieldValues) {
        this.filePath = filePath;
        this.fieldName = fieldName;
        this.fieldValues = fieldValues;
    }

    @Override
    public String getType() {
        return DATA_SOURCE_TYPE;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFieldName() {
        return fieldName;
    }

    public List<String> getFieldValues() {
        return fieldValues;
    }
}
