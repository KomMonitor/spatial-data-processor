package org.n52.kommonitor.spatialdataprocessor.controller;

import org.n52.kommonitor.models.Process;
import org.n52.kommonitor.spatialdataprocessor.api.ProcessesApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Controller implementing ProcessesApi
 *
 * @see org.n52.kommonitor.spatialdataprocessor.api.ProcessesApi
 */
@Controller
public class ProcessesController implements ProcessesApi {

    @Override
    public ResponseEntity<List<Process>> getAllProcessDescriptions() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
