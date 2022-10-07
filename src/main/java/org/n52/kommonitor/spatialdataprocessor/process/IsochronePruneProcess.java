package org.n52.kommonitor.spatialdataprocessor.process;

import org.n52.kommonitor.models.IsochronePruneProcessType;
import org.n52.kommonitor.spatialdataprocessor.util.ProcessorUtils;
import org.n52.kommonitor.spatialdataprocessor.util.datamanagement.DataManagementClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Implements a process to cut isochrones with spatial units
 */
public class IsochronePruneProcess implements Process<IsochronePruneProcessType> {

    public static final UUID id =
            UUID.nameUUIDFromBytes(IsochronePruneProcess.class.getName().getBytes(StandardCharsets.UTF_8));
    private static final Logger LOGGER = LoggerFactory.getLogger(IsochronePruneProcess.class);
    private final IsochronePruneProcessType parameters;
    private final DataManagementClient dmc;

    public IsochronePruneProcess(IsochronePruneProcessType parameters, DataManagementClient dmc) {
        this.parameters = parameters;
        this.dmc = dmc;
    }

    @Override
    public Object execute() throws Exception {

        LOGGER.debug(parameters.getIsochron().toString());
        LOGGER.debug(parameters.getSpatialUnit().toString());
        LOGGER.debug(parameters.getIndicator().toString());
        LOGGER.debug(parameters.getConfiguration().toString());

        Thread.sleep(1000);
        throw new RuntimeException("not implemented yet!");
    }
}
