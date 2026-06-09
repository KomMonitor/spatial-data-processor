package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.n52.kommonitor.models.IndicatorCoverageValueType;
import org.n52.kommonitor.models.TimeseriesType;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SpatialUnitCoverageType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T22:37:08.978103200+02:00[Europe/Berlin]", comments = "Generator version: 7.18.0")
public class SpatialUnitCoverageType {

  private String spatialUnitFeatureId;

  @Valid
  private List<@Valid TimeseriesType> timeseries = new ArrayList<>();

  @Valid
  private List<@Valid IndicatorCoverageValueType> coverage = new ArrayList<>();

  public SpatialUnitCoverageType() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SpatialUnitCoverageType(String spatialUnitFeatureId) {
    this.spatialUnitFeatureId = spatialUnitFeatureId;
  }

  public SpatialUnitCoverageType spatialUnitFeatureId(String spatialUnitFeatureId) {
    this.spatialUnitFeatureId = spatialUnitFeatureId;
    return this;
  }

  /**
   * Unique identifier for the spatial unit. This ID can be used to identify a spatial unit at the  DataManagement API. 
   * @return spatialUnitFeatureId
   */
  @NotNull 
  @Schema(name = "spatialUnitFeatureId", description = "Unique identifier for the spatial unit. This ID can be used to identify a spatial unit at the  DataManagement API. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("spatialUnitFeatureId")
  public String getSpatialUnitFeatureId() {
    return spatialUnitFeatureId;
  }

  public void setSpatialUnitFeatureId(String spatialUnitFeatureId) {
    this.spatialUnitFeatureId = spatialUnitFeatureId;
  }

  public SpatialUnitCoverageType timeseries(List<@Valid TimeseriesType> timeseries) {
    this.timeseries = timeseries;
    return this;
  }

  public SpatialUnitCoverageType addTimeseriesItem(TimeseriesType timeseriesItem) {
    if (this.timeseries == null) {
      this.timeseries = new ArrayList<>();
    }
    this.timeseries.add(timeseriesItem);
    return this;
  }

  /**
   * The indicator timeseries values for the spatial unit feature for which coverage fractions are calculated. 
   * @return timeseries
   */
  @Valid 
  @Schema(name = "timeseries", description = "The indicator timeseries values for the spatial unit feature for which coverage fractions are calculated. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timeseries")
  public List<@Valid TimeseriesType> getTimeseries() {
    return timeseries;
  }

  public void setTimeseries(List<@Valid TimeseriesType> timeseries) {
    this.timeseries = timeseries;
  }

  public SpatialUnitCoverageType coverage(List<@Valid IndicatorCoverageValueType> coverage) {
    this.coverage = coverage;
    return this;
  }

  public SpatialUnitCoverageType addCoverageItem(IndicatorCoverageValueType coverageItem) {
    if (this.coverage == null) {
      this.coverage = new ArrayList<>();
    }
    this.coverage.add(coverageItem);
    return this;
  }

  /**
   * Indicator coverage for this spatial unit. 
   * @return coverage
   */
  @Valid 
  @Schema(name = "coverage", description = "Indicator coverage for this spatial unit. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("coverage")
  public List<@Valid IndicatorCoverageValueType> getCoverage() {
    return coverage;
  }

  public void setCoverage(List<@Valid IndicatorCoverageValueType> coverage) {
    this.coverage = coverage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpatialUnitCoverageType spatialUnitCoverageType = (SpatialUnitCoverageType) o;
    return Objects.equals(this.spatialUnitFeatureId, spatialUnitCoverageType.spatialUnitFeatureId) &&
        Objects.equals(this.timeseries, spatialUnitCoverageType.timeseries) &&
        Objects.equals(this.coverage, spatialUnitCoverageType.coverage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(spatialUnitFeatureId, timeseries, coverage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SpatialUnitCoverageType {\n");
    sb.append("    spatialUnitFeatureId: ").append(toIndentedString(spatialUnitFeatureId)).append("\n");
    sb.append("    timeseries: ").append(toIndentedString(timeseries)).append("\n");
    sb.append("    coverage: ").append(toIndentedString(coverage)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

