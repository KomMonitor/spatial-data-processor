package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.n52.kommonitor.models.IndicatorCoverageValueType;
import org.n52.kommonitor.models.SpatialUnitCoverageType;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PoiCoverageType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T22:37:08.978103200+02:00[Europe/Berlin]", comments = "Generator version: 7.18.0")
public class PoiCoverageType {

  private String poiFeatureId;

  @Valid
  private List<@Valid IndicatorCoverageValueType> overallCoverage = new ArrayList<>();

  @Valid
  private List<@Valid SpatialUnitCoverageType> spatialUnitCoverage = new ArrayList<>();

  public PoiCoverageType() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PoiCoverageType(String poiFeatureId) {
    this.poiFeatureId = poiFeatureId;
  }

  public PoiCoverageType poiFeatureId(String poiFeatureId) {
    this.poiFeatureId = poiFeatureId;
    return this;
  }

  /**
   * Get poiFeatureId
   * @return poiFeatureId
   */
  @NotNull 
  @Schema(name = "poiFeatureId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("poiFeatureId")
  public String getPoiFeatureId() {
    return poiFeatureId;
  }

  public void setPoiFeatureId(String poiFeatureId) {
    this.poiFeatureId = poiFeatureId;
  }

  public PoiCoverageType overallCoverage(List<@Valid IndicatorCoverageValueType> overallCoverage) {
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
  @Schema(name = "overallCoverage", description = "Indicates the coverage for the entire spatial unit, which means the coverage of a single isochron for all spatial unit features. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("overallCoverage")
  public List<@Valid IndicatorCoverageValueType> getOverallCoverage() {
    return overallCoverage;
  }

  public void setOverallCoverage(List<@Valid IndicatorCoverageValueType> overallCoverage) {
    this.overallCoverage = overallCoverage;
  }

  public PoiCoverageType spatialUnitCoverage(List<@Valid SpatialUnitCoverageType> spatialUnitCoverage) {
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
  @Schema(name = "spatialUnitCoverage", description = "Indicates the coverage for single spatial unit features, which means the coverage of a single isochron for each feature of a spatial unit. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("spatialUnitCoverage")
  public List<@Valid SpatialUnitCoverageType> getSpatialUnitCoverage() {
    return spatialUnitCoverage;
  }

  public void setSpatialUnitCoverage(List<@Valid SpatialUnitCoverageType> spatialUnitCoverage) {
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

