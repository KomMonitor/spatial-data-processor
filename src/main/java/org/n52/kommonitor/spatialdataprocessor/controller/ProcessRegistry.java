package org.n52.kommonitor.spatialdataprocessor.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import org.n52.kommonitor.models.ProcessOverviewType;
import org.n52.kommonitor.spatialdataprocessor.process.IsochronePruneProcess;
import org.n52.kommonitor.spatialdataprocessor.process.Process;
import org.n52.kommonitor.spatialdataprocessor.process.TestProcess;

/**
 * Static Registry providing information about all currently available Processes
 */
public class ProcessRegistry {

    private static final Set<ProcessDescription> availableProcesses;

    static {
        availableProcesses = new HashSet<>();

        availableProcesses.add(
                new ProcessDescription(
                        IsochronePruneProcess.name,
                        IsochronePruneProcess::new,
                        new ProcessOverviewType()
                                .id(IsochronePruneProcess.id)
                                .name(IsochronePruneProcess.name)
                                .description(IsochronePruneProcess.class.getName())
                ));

        availableProcesses.add(
                new ProcessDescription(
                        TestProcess.name,
                        TestProcess::new,
                        new ProcessOverviewType()
                                .id(TestProcess.id)
                                .name(TestProcess.name)
                                .description(TestProcess.class.getName())
                ));

    }

    static Set<ProcessDescription> getProcesses() {
        return availableProcesses;
    }

    static class ProcessDescription {
        private final String name;
        private final ProcessOverviewType overviewType;
        private final Supplier<Process> supplier;

        private ProcessDescription(String name,
                                   Supplier<Process> supplier,
                                   ProcessOverviewType overviewType) {
            this.name = name;
            this.overviewType = overviewType;
            this.supplier = supplier;
        }

        public String getName() {
            return name;
        }

        public ProcessOverviewType getOverviewType() {
            return overviewType;
        }

        public Supplier<Process> getSupplier() {
            return supplier;
        }
    }
}
