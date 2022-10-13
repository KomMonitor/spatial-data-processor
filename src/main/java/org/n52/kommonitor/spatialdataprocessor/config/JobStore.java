package org.n52.kommonitor.spatialdataprocessor.config;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.n52.kommonitor.models.JobOverviewType;
import org.n52.kommonitor.spatialdataprocessor.util.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobStore.class);

    /**
     * Local storage of Jobs and their results.
     */
    private final Map<Job<?>, Future<?>> jobs = new ConcurrentHashMap<>();
    private final long holdingTime;

    public JobStore(@Value("${config.jobstore.holdingTime:10}") long holdingTime) {
        this.holdingTime = holdingTime;
    }

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
        LOGGER.debug("Start cleaning of JobStore at: " + OffsetDateTime.now());
        OffsetDateTime holdingTime = OffsetDateTime.now().minus(this.holdingTime, ChronoUnit.MINUTES);
        jobs.entrySet().removeIf((jobFutureEntry -> {
            Job<?> job = jobFutureEntry.getKey();

            // Keep all queued and running  Jobs
            if (job.getStatus().equals(JobOverviewType.StatusEnum.FINISHED)
                    || job.getStatus().equals(JobOverviewType.StatusEnum.QUEUED)) {
                return false;
            }
            // remove all Jobs that are done since over x minutes
            return job.getTimestamp().isBefore(holdingTime);
        }));
        LOGGER.debug("Finished cleaning of JobStore at: " + OffsetDateTime.now());
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
