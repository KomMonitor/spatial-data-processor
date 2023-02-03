package org.n52.kommonitor.dataloader;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "kommonitor.processor.residential-area-data.shape")
    @ConditionalOnProperty(
            value="kommonitor.processor.residential-area-data.type",
            havingValue = ShapeFileDataSource.DATA_SOURCE_TYPE,
            matchIfMissing = false)
    public FeatureDataSource shapeDataSource() {
        return new ShapeFileDataSource();
    }

}
