package org.n52.kommonitor.spatialdataprocessor.process;

import org.n52.kommonitor.models.JobInputType;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class IsochronePruneProcessor implements Process {

    public static final UUID id =
            UUID.nameUUIDFromBytes(IsochronePruneProcessor.class.getName().getBytes(StandardCharsets.UTF_8));

    public static final String name = "isochrone-prune";

    @Override
    public void run(JobInputType parameters) throws Exception {
        System.out.println("test");
        Thread.sleep(100000);

    }
}
