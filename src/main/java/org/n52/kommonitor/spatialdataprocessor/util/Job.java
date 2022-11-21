package org.n52.kommonitor.spatialdataprocessor.util;

import org.n52.kommonitor.models.JobOverviewType;
import org.n52.kommonitor.models.ProcessType;
import org.n52.kommonitor.spatialdataprocessor.process.IsochronePruneProcess;
import org.n52.kommonitor.spatialdataprocessor.process.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Job to be executed by the API
 */
public class Job<T extends ProcessType> implements Callable<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Job.class);

    protected UUID id = UUID.randomUUID();
    protected JobOverviewType.StatusEnum status = JobOverviewType.StatusEnum.QUEUED;
    private final Process<T> process;
    private final OffsetDateTime timestamp;

    public Job(Process<T> process) {
        this.process = process;
        this.timestamp = OffsetDateTime.now();
    }

    @Override
    public Object call() {
        LOGGER.info("Running Job#" + id);
        status = JobOverviewType.StatusEnum.RUNNING;
        try {
            Object result = process.execute();
            status = JobOverviewType.StatusEnum.FINISHED;
            LOGGER.info("Finished Job#" + id);
            return result;
        } catch (Exception e) {
            LOGGER.error("Failing Job#" + id + ": ",e);
            status = JobOverviewType.StatusEnum.FAILED;
            return null;
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
    public JobOverviewType.StatusEnum getStatus() {
        return status;
    }

    public Process<T> getProcess() {
        return process;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }
}
