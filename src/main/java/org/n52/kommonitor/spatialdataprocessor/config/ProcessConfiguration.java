package org.n52.kommonitor.spatialdataprocessor.config;

import org.n52.kommonitor.models.IsochronePruneProcessType;
import org.n52.kommonitor.models.ProcessOverviewType;
import org.n52.kommonitor.models.ProcessType;
import org.n52.kommonitor.models.TestProcessType;
import org.n52.kommonitor.spatialdataprocessor.operations.SpatialOperationUtils;
import org.n52.kommonitor.spatialdataprocessor.process.IsochronePruneProcess;
import org.n52.kommonitor.spatialdataprocessor.process.Process;
import org.n52.kommonitor.spatialdataprocessor.process.TestProcess;
import org.n52.kommonitor.spatialdataprocessor.util.FeatureUtils;
import org.n52.kommonitor.spatialdataprocessor.util.datamanagement.DataManagementClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Static Registry providing information about all currently available Processes
 */
@Component
public class ProcessConfiguration {

    public interface ProcessFactory<T extends ProcessType> {

        /**
         * Gets the name of the process for identification/referencing
         * @return name of the process this factory creates
         */
        String getProcessName();

        /**
         * Creates a new Process Instance
         *
         * @param processType ProcessType
         * @param metadata Additional metadata (e.g. User information)
         * @return new Process
         */
        Process<T> createProcess(T processType, Map<String, Object> metadata);

        /**
         * Get Overview of Process
         * @return ProcessOverview
         */
        ProcessOverviewType getProcessOverview();
    }

    @Bean
    public ProcessFactory<IsochronePruneProcessType> IsochronePruneProcessFactory(DataManagementClient dmc,
                                                                                  SpatialOperationUtils operationUtils,
                                                                                  FeatureUtils featureUtils) {
        return new ProcessFactory<>() {

            private final String name = "isochrone-prune";

            @Override
            public String getProcessName() {
                return name;
            }

            @Override
            public Process<IsochronePruneProcessType> createProcess(IsochronePruneProcessType parameters,
                                                                    Map<String, Object> metadata) {
                return new IsochronePruneProcess(parameters, dmc, operationUtils, featureUtils, metadata);
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
            public Process<TestProcessType> createProcess(TestProcessType processType, Map<String, Object> metadata) {
                return new TestProcess(dmc, metadata);
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
