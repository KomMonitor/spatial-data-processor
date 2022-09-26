package org.n52.kommonitor.spatialdataprocessor.process;

import org.n52.kommonitor.models.JobInputType;

public interface Process {

    void run(JobInputType parameters) throws Exception;
}
