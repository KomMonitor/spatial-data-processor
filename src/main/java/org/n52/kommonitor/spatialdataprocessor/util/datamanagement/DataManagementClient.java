package org.n52.kommonitor.spatialdataprocessor.util.datamanagement;


import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.n52.kommonitor.models.IndicatorOverviewType;
import org.n52.kommonitor.models.SpatialUnitOverviewType;
import org.n52.kommonitor.spatialdataprocessor.util.FeatureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Lightweight client for accessing kommonitor-data-management api.
 */
@Component
public class DataManagementClient {

	private static final Logger log = LoggerFactory.getLogger(DataManagementClient.class);

    private final DataManagementService service;
    private final FeatureUtils featureUtils = new FeatureUtils();

 // Inject Spring's auto-configured ObjectMapper instead of creating a new one
    public DataManagementClient(
            @Value("${config.data-management.baseUrl}") String baseUrl, 
            ObjectMapper springManagedMapper) {
        
        // Bridge OkHttp logging directly to Spring's SLF4J logger
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> log.debug("OkHttp: {}", message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // Set to BODY temporarily to see the full request/response

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl) // Note: Ensure baseUrl ends with a trailing slash '/' in your application.properties
                .addConverterFactory(JacksonConverterFactory.create(springManagedMapper))
                .client(httpClient)
                .build();

        service = retrofit.create(DataManagementService.class);
    }

    public SpatialUnitOverviewType getSpatialUnitOverview(UUID id, Optional<String> jwtToken) throws IOException {

    	if(jwtToken == null || jwtToken.isEmpty()) {
    		return bodyOrError(service.getPublicSpatialUnitById(id).execute());
    	}
        return bodyOrError(service.getSpatialUnitById(jwtToken.orElse(""), id).execute());
    }

    public ObjectNode getSpatialUnitGeoJSON(UUID spatialUnitId, Optional<String> jwtToken) throws IOException {
    	if(jwtToken == null || jwtToken.isEmpty()) {
    		return bodyOrError(service.getPublicSpatialUnitGeoJSON(spatialUnitId, featureUtils.getSimplifyGeometries()).execute());
    	}
    	return bodyOrError(service.getSpatialUnitGeoJSON(jwtToken.orElse(""), spatialUnitId, featureUtils.getSimplifyGeometries()).execute());
    }
    
    public ObjectNode getSpatialUnitGeoJSON_forDate(UUID spatialUnitId, 
    		int year,
            int month,
            int day,
            Optional<String> jwtToken) throws IOException {
    	if(jwtToken == null || jwtToken.isEmpty()) {
    		return bodyOrError(service.getPublicSpatialUnitGeoJSON_forDate(spatialUnitId, year, month, day, featureUtils.getSimplifyGeometries()).execute());
    	}
    	return bodyOrError(service.getSpatialUnitGeoJSON_forDate(jwtToken.orElse(""), spatialUnitId, year, month, day, featureUtils.getSimplifyGeometries()).execute());
    }

    public ObjectNode getSpatialUnitGeoJSONForIndicator(UUID indicatorId,
                                                        UUID spatialUnitId,
                                                        Optional<String> jwtToken) throws IOException {
    	if(jwtToken == null || jwtToken.isEmpty()) {
    		return bodyOrError(service.getPublicSpatialUnitGeoJSONForIndicator(indicatorId, spatialUnitId, featureUtils.getSimplifyGeometries()).execute());
    	}
    	return bodyOrError(service.getSpatialUnitGeoJSONForIndicator(
                jwtToken.orElse(""),
                indicatorId,
                spatialUnitId,
                featureUtils.getSimplifyGeometries()).execute()
        );
    }

    public ArrayNode getIndicatorTimeseries(UUID indicatorId,
                                            UUID spatialUnitId,
                                            Optional<String> jwtToken) throws IOException {
    	if(jwtToken == null || jwtToken.isEmpty()) {
    		return bodyOrError(service.getPublicIndicatorTimeseries(indicatorId, spatialUnitId).execute());
    	}
    	return bodyOrError(service.getIndicatorTimeseries(
                jwtToken.orElse(""),
                indicatorId,
                spatialUnitId).execute()
        );
    }
    
    public ArrayNode getIndicatorTimeseries_forDate(UUID indicatorId,
            UUID spatialUnitId,
            int year,
            int month,
            int day,
            Optional<String> jwtToken) throws IOException {
		if(jwtToken == null || jwtToken.isEmpty()) {
		return bodyOrError(service.getPublicIndicatorTimeseries_forDate(indicatorId, spatialUnitId, year, month, day).execute());
		}
		return bodyOrError(service.getIndicatorTimeseries_forDate(
		jwtToken.orElse(""),
		indicatorId,
		spatialUnitId, year, month, day).execute()
		);
	}

    public ObjectNode getSpatialUnitGeoJSONForIndicatorAndDate(UUID indicatorId,
                                                               UUID spatialUnitId,
                                                               int year,
                                                               int month,
                                                               int day,
                                                               Optional<String> jwtToken) throws Exception {
    	if(jwtToken == null || jwtToken.isEmpty()) {
    		return bodyOrError(service.getPublicSpatialUnitGeoJSONForIndicatorAndDate(indicatorId, 
    				spatialUnitId, year, month, day, featureUtils.getSimplifyGeometries()).execute());
    	}
    	return bodyOrError(service.getSpatialUnitGeoJSONForIndicatorAndDate(
                        jwtToken.orElse(""),
                        indicatorId,
                        spatialUnitId,
                        year,
                        month,
                        day, 
                        featureUtils.getSimplifyGeometries())
                .execute());
    }

    public IndicatorOverviewType getIndicatorOverview(UUID id, Optional<String> jwtToken) throws IOException {
        return bodyOrError(service.getIndicatorById(jwtToken.orElse(""), id).execute());
    }

    private <T> T bodyOrError(Response<T> response) throws IOException {
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new IOException("Request to data-management failed with status: " + response.code());
        }
    }
}
