package org.n52.kommonitor.spatialdataprocessor.util.datamanagement;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.n52.kommonitor.models.IndicatorOverviewType;
import org.n52.kommonitor.models.SpatialUnitOverviewType;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import java.util.UUID;

/**
 * Interface to kommonitor-data-management api. Only provides a small subset of functionality for simplicity
 */
public interface DataManagementService {

        @Headers({"Accept: application/json"})
        @GET("public/spatial-units/{spatialUnitId}")
        Call<SpatialUnitOverviewType> getSpatialUnitById(
                @Path("spatialUnitId") UUID spatialUnitId
        );

        @Headers({"Accept: application/json"})
        @GET("public/indicators/{indicatorId}")
        Call<IndicatorOverviewType> getIndicatorById(
                @Path("indicatorId") UUID indicatorId
        );

        @Headers({"Accept: */*"})
        @GET("public/indicators/{indicatorId}/{spatialUnitId}")
        Call<ObjectNode> getSpatialUnitGeoJSONForIndicator(
                @Path("indicatorId") UUID indicatorId,
                @Path("spatialUnitId") UUID spatialUnitId
        );

}