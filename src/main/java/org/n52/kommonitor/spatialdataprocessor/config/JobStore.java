package org.n52.kommonitor.spatialdataprocessor.config;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.n52.kommonitor.models.JobOverviewType;
import org.n52.kommonitor.spatialdataprocessor.util.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobStore.class);

    /**
     * Local storage of Jobs and their results.
     */
    private final Map<Job<?>, Future<?>> jobs = new HashMap<>();

    public void addJob(Job<?> job, Future<?> result) {
        jobs.put(job, result);
    }

    public List<JobOverviewType> getAllJobsOverview() {
        return jobs.entrySet()
                   .stream()
                   .map(e -> this.toOverviewType(e.getKey(), e.getValue()))
                   .collect(Collectors.toList());
    }

    public Optional<JobOverviewType> getJobOverview(UUID jobId) {
        Optional<Map.Entry<Job<?>, Future<?>>> job = jobs.entrySet()
                                                         .stream()
                                                         .filter(j -> j.getKey().getId().equals(jobId))
                                                         .findFirst();
        return job.map(jobFutureEntry -> toOverviewType(jobFutureEntry.getKey(), jobFutureEntry.getValue()));
    }

    @Scheduled(fixedDelay = 1000 * 60 * 2)
    private void cleanJobStore() {
        LOGGER.debug("TODO: implement cleanup of JobStore");
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
