package org.n52.kommonitor.spatialdataprocessor.util;

import org.n52.kommonitor.models.JobOverviewType;
import org.n52.kommonitor.models.ProcessType;
import org.n52.kommonitor.spatialdataprocessor.process.IsochronePruneProcess;
import org.n52.kommonitor.spatialdataprocessor.process.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Job to be executed by the API
 */
public class Job<T extends ProcessType> implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsochronePruneProcess.class);

    protected UUID id = UUID.randomUUID();
    protected JobOverviewType.StatusEnum status = JobOverviewType.StatusEnum.QUEUED;
    private final ProcessorUtils processorUtils;
    private final Process<T> process;
    private final T processConfig;
    private final OffsetDateTime timestamp;

    public Job(ProcessorUtils processorUtils, Process<T> process, T processConfig) {
        this.processorUtils = processorUtils;
        this.process = process;
        this.processConfig = processConfig;
        this.timestamp = OffsetDateTime.now();
    }

    @Override
    public void run() {
        LOGGER.info("Running Job#" + id);
        status = JobOverviewType.StatusEnum.RUNNING;
        try {
            process.execute(processorUtils, processConfig);
            status = JobOverviewType.StatusEnum.FINISHED;
        } catch (Exception e) {
            LOGGER.error("Failing Job#" + id + ": " + e.getMessage());
            status = JobOverviewType.StatusEnum.FAILED;
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
