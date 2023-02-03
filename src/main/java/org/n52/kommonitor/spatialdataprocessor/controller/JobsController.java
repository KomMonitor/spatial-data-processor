package org.n52.kommonitor.spatialdataprocessor.controller;

import org.n52.kommonitor.models.JobResultType;
import org.n52.kommonitor.models.ProcessType;
import org.n52.kommonitor.models.JobOverviewType;
import org.n52.kommonitor.spatialdataprocessor.api.JobsApi;
import org.n52.kommonitor.spatialdataprocessor.config.JobStore;
import org.n52.kommonitor.spatialdataprocessor.config.ProcessConfiguration;
import org.n52.kommonitor.spatialdataprocessor.util.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Controller implementing JobsApi.
 *
 * @see org.n52.kommonitor.spatialdataprocessor.api.JobsApi
 */
@Controller
public class JobsController implements JobsApi {

    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Map<String, ProcessConfiguration.ProcessFactory<?>> processFactories;
    private final JobStore jobStore;

    public JobsController(List<ProcessConfiguration.ProcessFactory<?>> factories, JobStore jobStore) {
        this.jobStore = jobStore;
        processFactories = new HashMap<>();
        for (ProcessConfiguration.ProcessFactory<?> processFactory : factories) {
            processFactories.put(processFactory.getProcessName(), processFactory);
        }
    }

    @Override
    public ResponseEntity<UUID> enqueueJob(ProcessType jobDefinition,
                                           String authHeader) {

        // Get Process by name
        ProcessConfiguration.ProcessFactory factory = processFactories.get(jobDefinition.getName());

        // Create metadata that may be used by processes
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("authHeader", authHeader);

        if (factory != null) {
            Job<?> job = new Job(factory.createProcess(jobDefinition, metadata));
            jobStore.addJob(job, executor.submit(job));
            return ResponseEntity.ok(job.getId());
        } else {
            //TODO: return nice error that process does not exist
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<List<JobOverviewType>> getAllJobs() {
        return ResponseEntity.ok(jobStore.getAllJobsOverview());
    }

    @Override
    public ResponseEntity<JobOverviewType> getJob(UUID jobId) {
        Optional<JobOverviewType> job = jobStore.getJobOverview(jobId);
        if (job.isPresent()) {
            return ResponseEntity.ok(job.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<JobResultType> getJobResult(UUID jobId) {
        Optional<JobResultType> result = jobStore.getJobResult(jobId);
        if (result.isPresent()) {
            if (result.get().getResult() != null){
                return ResponseEntity.ok(result.get());
            }
        }
        return ResponseEntity.notFound().build();
    }

}
