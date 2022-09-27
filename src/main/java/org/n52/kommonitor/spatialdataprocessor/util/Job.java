package org.n52.kommonitor.spatialdataprocessor.util;

import javafx.print.PrinterJob;
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
public class Job implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsochronePruneProcess.class);

    protected UUID id = UUID.randomUUID();
    protected JobOverviewType.StatusEnum status = JobOverviewType.StatusEnum.QUEUED;
    private final Process process;
    private final ProcessType jobConfiguration;
    private final OffsetDateTime timestamp;

    public Job(Process process, ProcessType jobConfiguration) {
        this.process = process;
        this.jobConfiguration = jobConfiguration;
        this.timestamp = OffsetDateTime.now();
    }

    @Override
    public void run() {
        LOGGER.info("Running Job#" + id);
        status = JobOverviewType.StatusEnum.RUNNING;
        try {
            process.run(jobConfiguration);
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

    public Process getProcess() {
        return process;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }
}
