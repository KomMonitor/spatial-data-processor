package org.n52.kommonitor.dataloader;

import java.util.List;

public class ShapeFileDataSource implements FeatureDataSource{

    public static final String DATA_SOURCE_TYPE = "esri_shapefile";

    private String filePath;
    private String fieldName;
    private List<String> fieldValues;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<String> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<String> fieldValues) {
        this.fieldValues = fieldValues;
    }

    @Override
    public String getType() {
        return DATA_SOURCE_TYPE;
    }
}
