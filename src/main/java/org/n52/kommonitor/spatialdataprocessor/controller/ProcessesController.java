package org.n52.kommonitor.spatialdataprocessor.controller;

import org.n52.kommonitor.models.ProcessOverviewType;
import org.n52.kommonitor.spatialdataprocessor.api.ProcessesApi;
import org.n52.kommonitor.spatialdataprocessor.process.IsochronePruneProcess;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller implementing ProcessesApi
 *
 * @see org.n52.kommonitor.spatialdataprocessor.api.ProcessesApi
 */
@Controller
public class ProcessesController implements ProcessesApi {

    @Override
    public ResponseEntity<List<ProcessOverviewType>> getAllProcessDescriptions() {
        return ResponseEntity.ok(ProcessRegistry.getProcesses()
                                                .stream()
                                                .map(ProcessRegistry.ProcessDescription::getOverviewType)
                                                .collect(Collectors.toList()));
    }
}
