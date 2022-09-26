package org.n52.kommonitor.spatialdataprocessor.controller;

import org.n52.kommonitor.models.JobOverviewType;
import org.n52.kommonitor.models.JobPOSTInputType;
import org.n52.kommonitor.spatialdataprocessor.api.JobsApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * Controller implementing JobsApi.
 *
 * @see org.n52.kommonitor.spatialdataprocessor.api.JobsApi
 */
@Controller
public class JobsController implements JobsApi {

    @Override
    public ResponseEntity<UUID> enqueueJob(JobPOSTInputType jobPOSTInputType) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<JobOverviewType> getAllJobs() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<JobOverviewType> getJob(UUID jobId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
