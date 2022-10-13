package org.n52.kommonitor.spatialdataprocessor.util.datamanagement;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.n52.kommonitor.models.IndicatorOverviewType;
import org.n52.kommonitor.models.SpatialUnitOverviewType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.UUID;

/**
 * Lightweight client for accessing kommonitor-data-management api.
 */
@Component
public class DataManagementClient {

    private final DataManagementService service;

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

    public SpatialUnitOverviewType getSpatialUnitOverview(UUID id) throws IOException {
        return bodyOrError(service.getSpatialUnitById(id).execute());
    }

    public ObjectNode getSpatialUnitGeoJSON(UUID indicatorId, UUID spatialUnitId) throws IOException {
        return bodyOrError(service.getSpatialUnitGeoJSONForIndicator(indicatorId, spatialUnitId).execute());
    }

    public IndicatorOverviewType getIndicatorOverview(UUID id) throws IOException {
        return bodyOrError(service.getIndicatorById(id).execute());
    }

    private <T> T bodyOrError(Response<T> response) throws IOException {
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new IOException("Request to data-management failed with status: " + response.code());
        }
    }
}
