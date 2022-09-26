package org.n52.kommonitor.spatialdataprocessor.controller;

import org.n52.kommonitor.models.ProcessOverviewType;
import org.n52.kommonitor.spatialdataprocessor.api.ProcessesApi;
import org.n52.kommonitor.spatialdataprocessor.process.IsochronePruneProcessor;
import org.n52.kommonitor.spatialdataprocessor.process.Process;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

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
                .id(IsochronePruneProcessor.id)
                .name(IsochronePruneProcessor.class.getName())
                .description(""));
    }


    @Override
    public ResponseEntity<List<ProcessOverviewType>> getAllProcessDescriptions() {
        return ResponseEntity.ok(availableProcesses);
    }
}
