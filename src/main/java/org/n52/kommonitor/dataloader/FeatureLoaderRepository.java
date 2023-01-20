package org.n52.kommonitor.dataloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FeatureLoaderRepository {

    @Autowired
    private List<FeatureLoader> featureLoaderList;

    /**
     * Retrieve a certain {@link FeatureLoader} implementation by a data source type
     *
     * @param datasourceType the type of  a certain {@link FeatureDataSource} implementation
     * @return an {@link Optional} describing the found {@link FeatureLoader} implementation
     */
    public Optional<FeatureLoader> getFeatureLoader(String datasourceType) {
        return this.featureLoaderList.stream()
                .filter(i -> i.getSupportedDataSource().equals(datasourceType))
                .findFirst();
    }

}
