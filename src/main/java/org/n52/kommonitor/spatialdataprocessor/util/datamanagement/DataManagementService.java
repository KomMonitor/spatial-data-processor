package org.n52.kommonitor.spatialdataprocessor.util.datamanagement;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.n52.kommonitor.models.IndicatorOverviewType;
import org.n52.kommonitor.models.SpatialUnitOverviewType;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import java.util.UUID;

/**
 * Interface to kommonitor-data-management api. Only provides a small subset of functionality for simplicity
 */
public interface DataManagementService {

        @Headers({"Accept: application/json"})
        @GET("spatial-units/{spatialUnitId}")
        Call<SpatialUnitOverviewType> getSpatialUnitById(
                @Header("Authorization") String jwt,
                @Path("spatialUnitId") UUID spatialUnitId
        );

        @Headers({"Accept: application/json"})
        @GET("spatial-units/{spatialUnitId}/allFeatures")
        Call<ObjectNode> getSpatialUnitGeoJSON(
                @Header("Authorization") String jwt,
                @Path("spatialUnitId") UUID spatialUnitId
        );

        @Headers({"Accept: application/json"})
        @GET("indicators/{indicatorId}")
        Call<IndicatorOverviewType> getIndicatorById(
                @Header("Authorization") String jwt,
                @Path("indicatorId") UUID indicatorId
        );

        @Headers({"Accept: */*"})
        @GET("indicators/{indicatorId}/{spatialUnitId}")
        Call<ObjectNode> getSpatialUnitGeoJSONForIndicator(
                @Header("Authorization") String jwt,
                @Path("indicatorId") UUID indicatorId,
                @Path("spatialUnitId") UUID spatialUnitId
        );

        @Headers({"Accept: */*"})
        @GET("indicators/{indicatorId}/{spatialUnitId}/without-geometry")
        Call<ArrayNode> getIndicatorTimeseries(
                @Header("Authorization") String jwt,
                @Path("indicatorId") UUID indicatorId,
                @Path("spatialUnitId") UUID spatialUnitId
        );

        @Headers({"Accept: */*"})
        @GET("indicators/{indicatorId}/{spatialUnitId}/{year}/{month}/{day}")
        Call<ObjectNode> getSpatialUnitGeoJSONForIndicatorAndDate(
                @Header("Authorization") String jwt,
                @Path("indicatorId") UUID indicatorId,
                @Path("spatialUnitId") UUID spatialUnitId,
                @Path("year") int year,
                @Path("month") int month,
                @Path("day") int day
        );

}
