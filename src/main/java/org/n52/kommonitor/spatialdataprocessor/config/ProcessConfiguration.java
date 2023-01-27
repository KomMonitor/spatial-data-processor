package org.n52.kommonitor.spatialdataprocessor.config;

import org.n52.kommonitor.dataloader.FeatureDataSource;
import org.n52.kommonitor.dataloader.FeatureLoaderRepository;
import org.n52.kommonitor.models.IsochronePruneProcessType;
import org.n52.kommonitor.models.ProcessOverviewType;
import org.n52.kommonitor.models.ProcessType;
import org.n52.kommonitor.models.TestProcessType;
import org.n52.kommonitor.spatialdataprocessor.operations.SpatialOperationUtils;
import org.n52.kommonitor.spatialdataprocessor.process.IsochronePruneProcess;
import org.n52.kommonitor.spatialdataprocessor.process.Process;
import org.n52.kommonitor.spatialdataprocessor.process.TestProcess;
import org.n52.kommonitor.spatialdataprocessor.util.FeatureUtils;
import org.n52.kommonitor.spatialdataprocessor.util.IsochroneUtils;
import org.n52.kommonitor.spatialdataprocessor.util.datamanagement.DataManagementClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Static Registry providing information about all currently available Processes
 */
@Component
public class ProcessConfiguration {

    public interface ProcessFactory<T extends ProcessType> {

        String getProcessName();

        Process<T> createProcess(T processType);

        ProcessOverviewType getProcessOverview();
    }

    @Bean
    public ProcessFactory<IsochronePruneProcessType> IsochronePruneProcessFactory(DataManagementClient dmc,
                                                                                  SpatialOperationUtils operationUtils,
                                                                                  FeatureUtils featureUtils,
                                                                                  IsochroneUtils isochroneUtils,
                                                                                  FeatureDataSource dataSource,
                                                                                  FeatureLoaderRepository repository) {
        return new ProcessFactory<>() {

            private final String name = "isochrone-prune";

            @Override
            public String getProcessName() {
                return name;
            }

            @Override
            public Process<IsochronePruneProcessType> createProcess(IsochronePruneProcessType parameters) {
                return new IsochronePruneProcess(parameters, dmc, operationUtils, featureUtils, isochroneUtils, dataSource, repository);
            }

            @Override
            public ProcessOverviewType getProcessOverview() {
                return new ProcessOverviewType()
                        .id(IsochronePruneProcess.id)
                        .name(name)
                        .description("Process for cutting isochrones with spatial units");
            }
        };
    }

    @Bean
    public ProcessFactory<TestProcessType> TestProcessFactory(DataManagementClient dmc) {
        return new ProcessFactory<>() {

            private final String name = "test";

            @Override
            public String getProcessName() {
                return name;
            }

            @Override
            public Process<TestProcessType> createProcess(TestProcessType processType) {
                return new TestProcess(dmc);
            }

            @Override
            public ProcessOverviewType getProcessOverview() {
                return new ProcessOverviewType()
                        .id(TestProcess.id)
                        .name(name)
                        .description("Dummy Process for testing Job Queue System.");
            }
        };
    }
}
