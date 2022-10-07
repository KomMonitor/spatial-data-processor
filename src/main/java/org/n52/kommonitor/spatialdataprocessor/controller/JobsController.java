package org.n52.kommonitor.spatialdataprocessor.controller;

import org.n52.kommonitor.models.ProcessType;
import org.n52.kommonitor.models.JobOverviewType;
import org.n52.kommonitor.spatialdataprocessor.api.JobsApi;
import org.n52.kommonitor.spatialdataprocessor.config.ProcessConfiguration;
import org.n52.kommonitor.spatialdataprocessor.util.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Controller implementing JobsApi.
 *
 * @see org.n52.kommonitor.spatialdataprocessor.api.JobsApi
 */
@Controller
public class JobsController implements JobsApi {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * Local storage of Jobs and their results.
     * TODO: refactor this to use a different storage solution
     */
    private final Map<Job<?>, Future<?>> jobs = new HashMap<>();
    private final Map<String, ProcessConfiguration.ProcessFactory<?>> processFactoryMap;

    public JobsController(List<ProcessConfiguration.ProcessFactory<?>> processFactories) {
        processFactoryMap = new HashMap<>();
        for (ProcessConfiguration.ProcessFactory<?> processFactory : processFactories) {
            processFactoryMap.put(processFactory.getProcessName(), processFactory);
        }
    }

    @Override
    public ResponseEntity<UUID> enqueueJob(ProcessType jobDefinition) {
        // Get Process by name
        ProcessConfiguration.ProcessFactory factory = processFactoryMap.get(jobDefinition.getName());

        if (factory != null) {
            Job<?> job = new Job(factory.createProcess(jobDefinition));
            jobs.put(job, executor.submit(job));
            return ResponseEntity.ok(job.getId());
        } else {
            //TODO: return nice error that process does not exist
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<List<JobOverviewType>> getAllJobs() {
        List<JobOverviewType> response = jobs.entrySet()
                                             .stream()
                                             .map(e -> this.toOverviewType(e.getKey(), e.getValue()))
                                             .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<JobOverviewType> getJob(UUID jobId) {
        Optional<Map.Entry<Job<?>, Future<?>>> job = jobs.entrySet()
                                                        .stream()
                                                        .filter(j -> j.getKey().getId().equals(jobId))
                                                        .findFirst();

        if (job.isPresent()) {
            return ResponseEntity.ok(this.toOverviewType(job.get().getKey(), job.get().getValue()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private JobOverviewType toOverviewType(Job<?> job, Future<?> resultFuture) {
        Object result;
        try {
            result = resultFuture.isDone() ? resultFuture.get() : null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new JobOverviewType()
                .id(job.getId())
                .process(job.getProcess().toString())
                .status(job.getStatus())
                .timestamp(job.getTimestamp())
                .result(result);
    }
}
