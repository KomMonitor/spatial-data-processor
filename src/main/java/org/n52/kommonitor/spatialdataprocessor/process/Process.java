package org.n52.kommonitor.spatialdataprocessor.process;

import org.n52.kommonitor.models.ProcessType;

public interface Process<T extends ProcessType> {

    void execute(T parameters) throws Exception;
}
