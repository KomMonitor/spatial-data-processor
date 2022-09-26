package org.n52.kommonitor.spatialdataprocessor.process;

import org.n52.kommonitor.models.JobInputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Job to be executed by the API
 */
public class Job implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsochronePruneProcess.class);

    protected UUID id = UUID.randomUUID();
    protected JobStatus status = JobStatus.queued;
    private final Process process;
    private final JobInputType jobConfiguration;

    public Job(Process process, JobInputType jobConfiguration) {
        this.process = process;
        this.jobConfiguration = jobConfiguration;
    }

    @Override
    public void run() {
        LOGGER.info("Running Job#" + id);
        status = JobStatus.running;
        try {
            process.run(jobConfiguration);
            status = JobStatus.finished;
        } catch (Exception e) {
            LOGGER.error("Failing Job#" + id + ": " + e.getMessage());
            status = JobStatus.failed;
        }
    }

    /**
     * Gets the unique id of the Job
     *
     * @return UUID of the Job
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the current status of the Job
     *
     * @return current status of the Job
     */
    public JobStatus getStatus() {
        return status;
    }

}
