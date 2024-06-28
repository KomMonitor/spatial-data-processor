package org.n52.kommonitor.spatialdataprocessor.util.datamanagement;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.n52.kommonitor.models.IndicatorOverviewType;
import org.n52.kommonitor.models.SpatialUnitOverviewType;
import org.n52.kommonitor.spatialdataprocessor.util.FeatureUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * Lightweight client for accessing kommonitor-data-management api.
 */
@Component
public class DataManagementClient {

    private final DataManagementService service;
    
    private FeatureUtils featureUtils = new FeatureUtils();

    public DataManagementClient(@Value("${config.data-management.baseUrl}") String baseUrl) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        // Magically find and add JSR310 Module to deserialize java8 LocalDate
        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

        // TODO: make this configurable. e.g. read out application.properties value
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(httpClient.build())
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
