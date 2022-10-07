package org.n52.kommonitor.spatialdataprocessor.controller;

import org.n52.kommonitor.models.ProcessOverviewType;
import org.n52.kommonitor.spatialdataprocessor.api.ProcessesApi;
import org.n52.kommonitor.spatialdataprocessor.config.ProcessConfiguration;
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

    private final List<ProcessOverviewType> overviewTypes;

    public ProcessesController(List<ProcessConfiguration.ProcessFactory<?>> processFactories) {
        overviewTypes = new ArrayList<>();
        for (ProcessConfiguration.ProcessFactory<?> processFactory : processFactories) {
            overviewTypes.add(processFactory.getProcessOverview());
        }
    }

    @Override
    public ResponseEntity<List<ProcessOverviewType>> getAllProcessDescriptions() {
        return ResponseEntity.ok(overviewTypes);
    }
}
