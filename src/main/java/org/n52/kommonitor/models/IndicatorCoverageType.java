package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.n52.kommonitor.models.IndicatorCoverageValueType;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * IndicatorCoverageType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-21T12:45:15.139999500+02:00[Europe/Berlin]")
public class IndicatorCoverageType {

  @JsonProperty("poiFeatureId")
  private String poiFeatureId;

  @JsonProperty("spatialUnitFeatureId")
  private String spatialUnitFeatureId;

  @JsonProperty("coverage")
  @Valid
  private List<IndicatorCoverageValueType> coverage = null;

  public IndicatorCoverageType poiFeatureId(String poiFeatureId) {
    this.poiFeatureId = poiFeatureId;
    return this;
  }

  /**
   * Get poiFeatureId
   * @return poiFeatureId
  */
  
  @Schema(name = "poiFeatureId", required = false)
  public String getPoiFeatureId() {
    return poiFeatureId;
  }

  public void setPoiFeatureId(String poiFeatureId) {
    this.poiFeatureId = poiFeatureId;
  }

  public IndicatorCoverageType spatialUnitFeatureId(String spatialUnitFeatureId) {
    this.spatialUnitFeatureId = spatialUnitFeatureId;
    return this;
  }

  /**
   * Get spatialUnitFeatureId
   * @return spatialUnitFeatureId
  */
  
  @Schema(name = "spatialUnitFeatureId", required = false)
  public String getSpatialUnitFeatureId() {
    return spatialUnitFeatureId;
  }

  public void setSpatialUnitFeatureId(String spatialUnitFeatureId) {
    this.spatialUnitFeatureId = spatialUnitFeatureId;
  }

  public IndicatorCoverageType coverage(List<IndicatorCoverageValueType> coverage) {
    this.coverage = coverage;
    return this;
  }

  public IndicatorCoverageType addCoverageItem(IndicatorCoverageValueType coverageItem) {
    if (this.coverage == null) {
      this.coverage = new ArrayList<>();
    }
    this.coverage.add(coverageItem);
    return this;
  }

  /**
   * Get coverage
   * @return coverage
  */
  @Valid 
  @Schema(name = "coverage", required = false)
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
    IndicatorCoverageType indicatorCoverageType = (IndicatorCoverageType) o;
    return Objects.equals(this.poiFeatureId, indicatorCoverageType.poiFeatureId) &&
        Objects.equals(this.spatialUnitFeatureId, indicatorCoverageType.spatialUnitFeatureId) &&
        Objects.equals(this.coverage, indicatorCoverageType.coverage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(poiFeatureId, spatialUnitFeatureId, coverage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndicatorCoverageType {\n");
    sb.append("    poiFeatureId: ").append(toIndentedString(poiFeatureId)).append("\n");
    sb.append("    spatialUnitFeatureId: ").append(toIndentedString(spatialUnitFeatureId)).append("\n");
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

