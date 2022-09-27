package org.n52.kommonitor.spatialdataprocessor.process;

import org.n52.kommonitor.models.IsochronePruneProcessType;
import org.n52.kommonitor.models.ProcessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Implements a process to cut isochrones with spatial units
 */
public class IsochronePruneProcess implements Process {

    public static final UUID id =
            UUID.nameUUIDFromBytes(IsochronePruneProcess.class.getName().getBytes(StandardCharsets.UTF_8));
    public static final String name = "isochrone-prune";
    private static final Logger LOGGER = LoggerFactory.getLogger(IsochronePruneProcess.class);

    @Override
    public void run(ProcessType parameters) throws Exception {
        IsochronePruneProcessType definition = (IsochronePruneProcessType) parameters;

        LOGGER.debug(definition.getIsochron().toString());
        LOGGER.debug(definition.getSpatialUnit().toString());
        LOGGER.debug(definition.getIndicator().toString());
        LOGGER.debug(definition.getConfiguration().toString());

        Thread.sleep(1000);
        throw new RuntimeException("not implemented yet!");
    }
}
