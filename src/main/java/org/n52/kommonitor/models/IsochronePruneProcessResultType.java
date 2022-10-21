package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.n52.kommonitor.models.IndicatorCoverageType;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * IsochronePruneProcessResultType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-21T12:45:15.139999500+02:00[Europe/Berlin]")
public class IsochronePruneProcessResultType {

  @JsonProperty("indicatorId")
  private UUID indicatorId;

  @JsonProperty("overallCoverage")
  private IndicatorCoverageType overallCoverage;

  @JsonProperty("poiCoverage")
  @Valid
  private List<IndicatorCoverageType> poiCoverage = null;

  @JsonProperty("spatialUnitCoverage")
  @Valid
  private List<IndicatorCoverageType> spatialUnitCoverage = null;

  public IsochronePruneProcessResultType indicatorId(UUID indicatorId) {
    this.indicatorId = indicatorId;
    return this;
  }

  /**
   * Get indicatorId
   * @return indicatorId
  */
  @Valid 
  @Schema(name = "indicatorId", required = false)
  public UUID getIndicatorId() {
    return indicatorId;
  }

  public void setIndicatorId(UUID indicatorId) {
    this.indicatorId = indicatorId;
  }

  public IsochronePruneProcessResultType overallCoverage(IndicatorCoverageType overallCoverage) {
    this.overallCoverage = overallCoverage;
    return this;
  }

  /**
   * Get overallCoverage
   * @return overallCoverage
  */
  @Valid 
  @Schema(name = "overallCoverage", required = false)
  public IndicatorCoverageType getOverallCoverage() {
    return overallCoverage;
  }

  public void setOverallCoverage(IndicatorCoverageType overallCoverage) {
    this.overallCoverage = overallCoverage;
  }

  public IsochronePruneProcessResultType poiCoverage(List<IndicatorCoverageType> poiCoverage) {
    this.poiCoverage = poiCoverage;
    return this;
  }

  public IsochronePruneProcessResultType addPoiCoverageItem(IndicatorCoverageType poiCoverageItem) {
    if (this.poiCoverage == null) {
      this.poiCoverage = new ArrayList<>();
    }
    this.poiCoverage.add(poiCoverageItem);
    return this;
  }

  /**
   * Get poiCoverage
   * @return poiCoverage
  */
  @Valid 
  @Schema(name = "poiCoverage", required = false)
  public List<IndicatorCoverageType> getPoiCoverage() {
    return poiCoverage;
  }

  public void setPoiCoverage(List<IndicatorCoverageType> poiCoverage) {
    this.poiCoverage = poiCoverage;
  }

  public IsochronePruneProcessResultType spatialUnitCoverage(List<IndicatorCoverageType> spatialUnitCoverage) {
    this.spatialUnitCoverage = spatialUnitCoverage;
    return this;
  }

  public IsochronePruneProcessResultType addSpatialUnitCoverageItem(IndicatorCoverageType spatialUnitCoverageItem) {
    if (this.spatialUnitCoverage == null) {
      this.spatialUnitCoverage = new ArrayList<>();
    }
    this.spatialUnitCoverage.add(spatialUnitCoverageItem);
    return this;
  }

  /**
   * Get spatialUnitCoverage
   * @return spatialUnitCoverage
  */
  @Valid 
  @Schema(name = "spatialUnitCoverage", required = false)
  public List<IndicatorCoverageType> getSpatialUnitCoverage() {
    return spatialUnitCoverage;
  }

  public void setSpatialUnitCoverage(List<IndicatorCoverageType> spatialUnitCoverage) {
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
    IsochronePruneProcessResultType isochronePruneProcessResultType = (IsochronePruneProcessResultType) o;
    return Objects.equals(this.indicatorId, isochronePruneProcessResultType.indicatorId) &&
        Objects.equals(this.overallCoverage, isochronePruneProcessResultType.overallCoverage) &&
        Objects.equals(this.poiCoverage, isochronePruneProcessResultType.poiCoverage) &&
        Objects.equals(this.spatialUnitCoverage, isochronePruneProcessResultType.spatialUnitCoverage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(indicatorId, overallCoverage, poiCoverage, spatialUnitCoverage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IsochronePruneProcessResultType {\n");
    sb.append("    indicatorId: ").append(toIndentedString(indicatorId)).append("\n");
    sb.append("    overallCoverage: ").append(toIndentedString(overallCoverage)).append("\n");
    sb.append("    poiCoverage: ").append(toIndentedString(poiCoverage)).append("\n");
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

