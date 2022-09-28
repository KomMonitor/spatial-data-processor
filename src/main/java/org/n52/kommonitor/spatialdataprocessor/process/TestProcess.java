package org.n52.kommonitor.spatialdataprocessor.process;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.n52.kommonitor.models.ProcessType;
import org.n52.kommonitor.models.TestProcessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestProcess implements Process<TestProcessType> {

    public static final UUID id =
            UUID.nameUUIDFromBytes(TestProcess.class.getName().getBytes(StandardCharsets.UTF_8));
    public static final String name = "test";
    private static final Logger LOGGER = LoggerFactory.getLogger(TestProcess.class);

    @Override
    public void execute(TestProcessType parameters) throws Exception {
        Thread.sleep(1000 * 30);
        throw new RuntimeException("not implemented yet!");
    }
}
