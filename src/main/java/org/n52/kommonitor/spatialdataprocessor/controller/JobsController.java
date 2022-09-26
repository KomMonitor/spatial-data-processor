package org.n52.kommonitor.spatialdataprocessor.controller;

import org.n52.kommonitor.models.JobInputType;
import org.n52.kommonitor.models.JobOverviewType;
import org.n52.kommonitor.spatialdataprocessor.api.JobsApi;
import org.n52.kommonitor.spatialdataprocessor.process.IsochronePruneProcess;
import org.n52.kommonitor.spatialdataprocessor.process.Job;
import org.n52.kommonitor.spatialdataprocessor.process.Process;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * Controller implementing JobsApi.
 *
 * @see org.n52.kommonitor.spatialdataprocessor.api.JobsApi
 */
@Controller
public class JobsController implements JobsApi {

    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Map<String, Supplier<Process>> availableProcesses;

    private Map<Job, Future<?>> jobs = new HashMap<>();

    JobsController() {
        this.availableProcesses = new HashMap<>();

        //TODO: make this dynamic instead of hardcoding
        availableProcesses.put(IsochronePruneProcess.name, IsochronePruneProcess::new);
    }

    @Override
    public ResponseEntity<UUID> enqueueJob(JobInputType jobDefinition) {
        // Get Process

        Supplier<Process> process = availableProcesses.get(jobDefinition.getName());
        if (process != null) {
            Job job = new Job(process.get(), jobDefinition);
            jobs.put(job, executor.submit(job));
            return ResponseEntity.ok(job.getId());
        } else {
            //TODO: return nice error that process does not exist
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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
