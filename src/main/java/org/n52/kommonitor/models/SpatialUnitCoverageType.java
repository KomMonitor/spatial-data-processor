package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.n52.kommonitor.models.IndicatorCoverageValueType;
import org.n52.kommonitor.models.TimeseriesType;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * SpatialUnitCoverageType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-05T08:16:59.005638400+02:00[Europe/Berlin]")
public class SpatialUnitCoverageType {

  @JsonProperty("spatialUnitFeatureId")
  private String spatialUnitFeatureId;

  @JsonProperty("timeseries")
  @Valid
  private List<TimeseriesType> timeseries = null;

  @JsonProperty("coverage")
  @Valid
  private List<IndicatorCoverageValueType> coverage = null;

  public SpatialUnitCoverageType spatialUnitFeatureId(String spatialUnitFeatureId) {
    this.spatialUnitFeatureId = spatialUnitFeatureId;
    return this;
  }

  /**
   * Unique identifier for the spatial unit. This ID can be used to identify a spatial unit at the  DataManagement API. 
   * @return spatialUnitFeatureId
  */
  @NotNull 
  @Schema(name = "spatialUnitFeatureId", description = "Unique identifier for the spatial unit. This ID can be used to identify a spatial unit at the  DataManagement API. ", required = true)
  public String getSpatialUnitFeatureId() {
    return spatialUnitFeatureId;
  }

  public void setSpatialUnitFeatureId(String spatialUnitFeatureId) {
    this.spatialUnitFeatureId = spatialUnitFeatureId;
  }

  public SpatialUnitCoverageType timeseries(List<TimeseriesType> timeseries) {
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
  @Schema(name = "timeseries", description = "The indicator timeseries values for the spatial unit feature for which coverage fractions are calculated. ", required = false)
  public List<TimeseriesType> getTimeseries() {
    return timeseries;
  }

  public void setTimeseries(List<TimeseriesType> timeseries) {
    this.timeseries = timeseries;
  }

  public SpatialUnitCoverageType coverage(List<IndicatorCoverageValueType> coverage) {
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
  @Schema(name = "coverage", description = "Indicator coverage for this spatial unit. ", required = false)
  public List<IndicatorCoverageValueType> getCoverage() {
    return coverage;
  }

  public void setCoverage(List<IndicatorCoverageValueType> coverage) {
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

