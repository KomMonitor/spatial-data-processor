package org.n52.kommonitor.spatialdataprocessor.controller;

import org.n52.kommonitor.models.ProcessOverviewType;
import org.n52.kommonitor.spatialdataprocessor.api.ProcessesApi;
import org.n52.kommonitor.spatialdataprocessor.process.IsochronePruneProcess;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller implementing ProcessesApi
 *
 * @see org.n52.kommonitor.spatialdataprocessor.api.ProcessesApi
 */
@Controller
public class ProcessesController implements ProcessesApi {

    private final List<ProcessOverviewType> availableProcesses;

    public ProcessesController() {

        this.availableProcesses = new ArrayList<>();

        //TODO: make this dynamic instead of hardcoding
        availableProcesses.add(new ProcessOverviewType()
                .id(IsochronePruneProcess.id)
                .name(IsochronePruneProcess.class.getName())
                .description(""));
    }


    @Override
    public ResponseEntity<List<ProcessOverviewType>> getAllProcessDescriptions() {
        return ResponseEntity.ok(availableProcesses);
    }
}
