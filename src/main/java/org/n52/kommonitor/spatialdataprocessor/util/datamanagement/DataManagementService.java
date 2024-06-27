package org.n52.kommonitor.spatialdataprocessor.util.datamanagement;

import java.util.UUID;

import org.n52.kommonitor.models.IndicatorOverviewType;
import org.n52.kommonitor.models.SpatialUnitOverviewType;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

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
        @GET("spatial-units/{spatialUnitId}/allFeatures?simplifyGeometries=medium")
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
        @GET("indicators/{indicatorId}/{spatialUnitId}?simplifyGeometries=medium")
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
        @GET("indicators/{indicatorId}/{spatialUnitId}/{year}/{month}/{day}?simplifyGeometries=medium")
        Call<ObjectNode> getSpatialUnitGeoJSONForIndicatorAndDate(
                @Header("Authorization") String jwt,
                @Path("indicatorId") UUID indicatorId,
                @Path("spatialUnitId") UUID spatialUnitId,
                @Path("year") int year,
                @Path("month") int month,
                @Path("day") int day
        );
        
        @Headers({"Accept: application/json"})
        @GET("public/spatial-units/{spatialUnitId}")
        Call<SpatialUnitOverviewType> getPublicSpatialUnitById(
                @Path("spatialUnitId") UUID spatialUnitId
        );

        @Headers({"Accept: application/json"})
        @GET("public/spatial-units/{spatialUnitId}/allFeatures?simplifyGeometries=medium")
        Call<ObjectNode> getPublicSpatialUnitGeoJSON(
                @Path("spatialUnitId") UUID spatialUnitId
        );

        @Headers({"Accept: application/json"})
        @GET("public/indicators/{indicatorId}")
        Call<IndicatorOverviewType> getPublicIndicatorById(
                @Path("indicatorId") UUID indicatorId
        );

        @Headers({"Accept: */*"})
        @GET("public/indicators/{indicatorId}/{spatialUnitId}?simplifyGeometries=medium")
        Call<ObjectNode> getPublicSpatialUnitGeoJSONForIndicator(
                @Path("indicatorId") UUID indicatorId,
                @Path("spatialUnitId") UUID spatialUnitId
        );

        @Headers({"Accept: */*"})
        @GET("public/indicators/{indicatorId}/{spatialUnitId}/without-geometry")
        Call<ArrayNode> getPublicIndicatorTimeseries(
                @Path("indicatorId") UUID indicatorId,
                @Path("spatialUnitId") UUID spatialUnitId
        );

        @Headers({"Accept: */*"})
        @GET("public/indicators/{indicatorId}/{spatialUnitId}/{year}/{month}/{day}?simplifyGeometries=medium")
        Call<ObjectNode> getPublicSpatialUnitGeoJSONForIndicatorAndDate(
                @Path("indicatorId") UUID indicatorId,
                @Path("spatialUnitId") UUID spatialUnitId,
                @Path("year") int year,
                @Path("month") int month,
                @Path("day") int day
        );

}
