package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.n52.kommonitor.models.IndicatorCoverageValueType;
import org.n52.kommonitor.models.SpatialUnitCoverageType;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * PoiCoverageType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-27T15:13:32.128850400+01:00[Europe/Berlin]")
public class PoiCoverageType {

  @JsonProperty("poiFeatureId")
  private String poiFeatureId;

  @JsonProperty("overallCoverage")
  @Valid
  private List<IndicatorCoverageValueType> overallCoverage = null;

  @JsonProperty("spatialUnitCoverage")
  @Valid
  private List<SpatialUnitCoverageType> spatialUnitCoverage = null;

  public PoiCoverageType poiFeatureId(String poiFeatureId) {
    this.poiFeatureId = poiFeatureId;
    return this;
  }

  /**
   * Get poiFeatureId
   * @return poiFeatureId
  */
  @NotNull 
  @Schema(name = "poiFeatureId", required = true)
  public String getPoiFeatureId() {
    return poiFeatureId;
  }

  public void setPoiFeatureId(String poiFeatureId) {
    this.poiFeatureId = poiFeatureId;
  }

  public PoiCoverageType overallCoverage(List<IndicatorCoverageValueType> overallCoverage) {
    this.overallCoverage = overallCoverage;
    return this;
  }

  public PoiCoverageType addOverallCoverageItem(IndicatorCoverageValueType overallCoverageItem) {
    if (this.overallCoverage == null) {
      this.overallCoverage = new ArrayList<>();
    }
    this.overallCoverage.add(overallCoverageItem);
    return this;
  }

  /**
   * Indicates the coverage for the entire spatial unit, which means the coverage of a single isochron for all spatial unit features. 
   * @return overallCoverage
  */
  @Valid 
  @Schema(name = "overallCoverage", description = "Indicates the coverage for the entire spatial unit, which means the coverage of a single isochron for all spatial unit features. ", required = false)
  public List<IndicatorCoverageValueType> getOverallCoverage() {
    return overallCoverage;
  }

  public void setOverallCoverage(List<IndicatorCoverageValueType> overallCoverage) {
    this.overallCoverage = overallCoverage;
  }

  public PoiCoverageType spatialUnitCoverage(List<SpatialUnitCoverageType> spatialUnitCoverage) {
    this.spatialUnitCoverage = spatialUnitCoverage;
    return this;
  }

  public PoiCoverageType addSpatialUnitCoverageItem(SpatialUnitCoverageType spatialUnitCoverageItem) {
    if (this.spatialUnitCoverage == null) {
      this.spatialUnitCoverage = new ArrayList<>();
    }
    this.spatialUnitCoverage.add(spatialUnitCoverageItem);
    return this;
  }

  /**
   * Indicates the coverage for single spatial unit features, which means the coverage of a single isochron for each feature of a spatial unit. 
   * @return spatialUnitCoverage
  */
  @Valid 
  @Schema(name = "spatialUnitCoverage", description = "Indicates the coverage for single spatial unit features, which means the coverage of a single isochron for each feature of a spatial unit. ", required = false)
  public List<SpatialUnitCoverageType> getSpatialUnitCoverage() {
    return spatialUnitCoverage;
  }

  public void setSpatialUnitCoverage(List<SpatialUnitCoverageType> spatialUnitCoverage) {
    this.spatialUnitCoverage = spatialUnitCoverage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PoiCoverageType poiCoverageType = (PoiCoverageType) o;
    return Objects.equals(this.poiFeatureId, poiCoverageType.poiFeatureId) &&
        Objects.equals(this.overallCoverage, poiCoverageType.overallCoverage) &&
        Objects.equals(this.spatialUnitCoverage, poiCoverageType.spatialUnitCoverage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(poiFeatureId, overallCoverage, spatialUnitCoverage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PoiCoverageType {\n");
    sb.append("    poiFeatureId: ").append(toIndentedString(poiFeatureId)).append("\n");
    sb.append("    overallCoverage: ").append(toIndentedString(overallCoverage)).append("\n");
    sb.append("    spatialUnitCoverage: ").append(toIndentedString(spatialUnitCoverage)).append("\n");
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

