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

    record ProcessDescription(String name,
                              Supplier<Process<?>> supplier,
                              ProcessOverviewType overviewType) {
    }
}
