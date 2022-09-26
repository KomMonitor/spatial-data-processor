package org.n52.kommonitor.spatialdataprocessor.process;

import org.n52.kommonitor.models.JobInputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class IsochronePruneProcess implements Process {

    public static final UUID id =
            UUID.nameUUIDFromBytes(IsochronePruneProcess.class.getName().getBytes(StandardCharsets.UTF_8));
    public static final String name = "isochrone-prune";
    private static final Logger LOGGER = LoggerFactory.getLogger(IsochronePruneProcess.class);

    @Override
    public void run(JobInputType parameters) throws Exception {
        Thread.sleep(1000);
        throw new RuntimeException("not implemented yet!");
    }
}
