package org.n52.kommonitor.spatialdataprocessor.process;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.n52.kommonitor.models.IndicatorOverviewType;
import org.n52.kommonitor.models.SpatialUnitOverviewType;
import org.n52.kommonitor.models.TestProcessType;
import org.n52.kommonitor.spatialdataprocessor.util.ProcessorUtils;
import org.n52.kommonitor.spatialdataprocessor.util.datamanagement.DataManagementClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestProcess implements Process<TestProcessType> {

    public static final UUID id =
            UUID.nameUUIDFromBytes(TestProcess.class.getName().getBytes(StandardCharsets.UTF_8));
    private static final Logger LOGGER = LoggerFactory.getLogger(TestProcess.class);
    private final DataManagementClient dataManagementClient;
    private final Optional<String> authHeader;

    public TestProcess(DataManagementClient dmc, Map<String, Object> metadata) {
        dataManagementClient = dmc;

        this.authHeader = metadata.containsKey("authHeader")?
                Optional.of(metadata.get("authHeader").toString()) : Optional.empty();
    }

    @Override
    public Object execute() throws Exception {

        // UUID indicatorId = UUID.fromString("baad078b-8e91-4999-aa94-0fee5a50cec6");
        UUID spatialUnitId = UUID.fromString("3933ca80-4a76-41b1-beeb-31ae108fa5a6");

        SpatialUnitOverviewType spatialUnit = dataManagementClient.getSpatialUnitOverview(spatialUnitId, authHeader);
        //LOGGER.info(spatialUnit.toString());

        // IndicatorOverviewType indicatorOverview = dataManagementClient.getIndicatorOverview(indicatorId, authHeader);
        //LOGGER.info(indicatorOverview.toString());

        ObjectNode spatialUnitGeoJSON = dataManagementClient.getSpatialUnitGeoJSON(spatialUnitId, authHeader);
        // LOGGER.info(spatialUnitGeoJSON.toString());

        // Test GeoJSON processing capabilities of locationtech JTS
        GeoJsonReader reader = new GeoJsonReader();
        Geometry read = reader.read(spatialUnitGeoJSON.toString());

        StringBuilder b = new StringBuilder();
        b.append("\n-----\n");
        b.append("Kowalski, analysis!\n");
        b.append("It seems they are ");
        b.append(read.equalsExact(read)? "": "NOT ");
        b.append("exactly the same Skipper.");
        b.append("\n-----\n");

        LOGGER.info(b.toString());

        Thread.sleep(1000 * 30);

        return "done";
    }
}
