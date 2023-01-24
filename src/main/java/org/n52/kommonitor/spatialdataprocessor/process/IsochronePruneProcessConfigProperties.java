package org.n52.kommonitor.spatialdataprocessor.process;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableConfigurationProperties
@ConfigurationProperties("kommonitor.processor")
public class IsochronePruneProcessConfigProperties {

    private ResidentialAreaData residentialAreaData;

    public ResidentialAreaData getResidentialAreaData() {
        return residentialAreaData;
    }

    public void setResidentialAreaData(ResidentialAreaData residentialAreaData) {
        this.residentialAreaData = residentialAreaData;
    }

    public static class ResidentialAreaData {
        private String type;
        private String filePath;
        private String fieldName;
        private List<String> fieldValues;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

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
    }
}
