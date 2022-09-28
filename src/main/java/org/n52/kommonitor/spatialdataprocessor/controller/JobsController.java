package org.n52.kommonitor.spatialdataprocessor.controller;

import org.n52.kommonitor.models.ProcessType;
import org.n52.kommonitor.models.JobOverviewType;
import org.n52.kommonitor.spatialdataprocessor.api.JobsApi;
import org.n52.kommonitor.spatialdataprocessor.util.Job;
import org.n52.kommonitor.spatialdataprocessor.process.Process;
import org.n52.kommonitor.spatialdataprocessor.util.ProcessorUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;
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
    private final ProcessorUtils processorUtils;

    public JobsController(ProcessorUtils processorUtils) {
        this.processorUtils = processorUtils;
    }

    @Override
    public ResponseEntity<UUID> enqueueJob(ProcessType jobDefinition) {
        // Get Process by name
        Optional<Supplier<Process<?>>> process = ProcessRegistry.getProcesses()
                                                   .stream()
                                                   .filter(pd -> pd.name().equals(jobDefinition.getName()))
                                                   .map(ProcessRegistry.ProcessDescription::supplier)
                                                   .findFirst();
        if (process.isPresent()) {
            Job<?> job = new Job(processorUtils, process.get().get(), jobDefinition);
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
                                                        .filter(j -> j.getKey().getId() == jobId)
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
