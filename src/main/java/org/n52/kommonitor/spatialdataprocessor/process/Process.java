package org.n52.kommonitor.spatialdataprocessor.process;

import org.n52.kommonitor.models.ProcessType;

public interface Process {

    void run(ProcessType parameters) throws Exception;
}
