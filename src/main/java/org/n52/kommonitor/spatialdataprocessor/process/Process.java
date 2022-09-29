package org.n52.kommonitor.spatialdataprocessor.process;

import org.n52.kommonitor.models.ProcessType;
import org.n52.kommonitor.spatialdataprocessor.util.ProcessorUtils;

public interface Process<T extends ProcessType> {

    Object execute(ProcessorUtils util, T parameters) throws Exception;
}
