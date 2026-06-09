package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.n52.kommonitor.models.OverallCoverageType;
import org.n52.kommonitor.models.PoiCoverageType;
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
 * IsochronePruneProcessResultType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T22:37:08.978103200+02:00[Europe/Berlin]", comments = "Generator version: 7.18.0")
public class IsochronePruneProcessResultType {

  private UUID indicatorId;

  @Valid
  private List<@Valid TimeseriesType> timeseries = new ArrayList<>();

  @Valid
  private List<@Valid OverallCoverageType> overallCoverage = new ArrayList<>();

  @Valid
  private List<@Valid PoiCoverageType> poiCoverage = new ArrayList<>();

  public IsochronePruneProcessResultType() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public IsochronePruneProcessResultType(UUID indicatorId) {
    this.indicatorId = indicatorId;
  }

  public IsochronePruneProcessResultType indicatorId(UUID indicatorId) {
    this.indicatorId = indicatorId;
    return this;
  }

  /**
   * Unique indicator identifier. Coverage values relates to this indicator. 
   * @return indicatorId
   */
  @NotNull @Valid 
  @Schema(name = "indicatorId", description = "Unique indicator identifier. Coverage values relates to this indicator. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("indicatorId")
  public UUID getIndicatorId() {
    return indicatorId;
  }

  public void setIndicatorId(UUID indicatorId) {
    this.indicatorId = indicatorId;
  }

  public IsochronePruneProcessResultType timeseries(List<@Valid TimeseriesType> timeseries) {
    this.timeseries = timeseries;
    return this;
  }

  public IsochronePruneProcessResultType addTimeseriesItem(TimeseriesType timeseriesItem) {
    if (this.timeseries == null) {
      this.timeseries = new ArrayList<>();
    }
    this.timeseries.add(timeseriesItem);
    return this;
  }

  /**
   * The indicator timeseries values for which coverage fractions are calculated. 
   * @return timeseries
   */
  @Valid 
  @Schema(name = "timeseries", description = "The indicator timeseries values for which coverage fractions are calculated. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timeseries")
  public List<@Valid TimeseriesType> getTimeseries() {
    return timeseries;
  }

  public void setTimeseries(List<@Valid TimeseriesType> timeseries) {
    this.timeseries = timeseries;
  }

  public IsochronePruneProcessResultType overallCoverage(List<@Valid OverallCoverageType> overallCoverage) {
    this.overallCoverage = overallCoverage;
    return this;
  }

  public IsochronePruneProcessResultType addOverallCoverageItem(OverallCoverageType overallCoverageItem) {
    if (this.overallCoverage == null) {
      this.overallCoverage = new ArrayList<>();
    }
    this.overallCoverage.add(overallCoverageItem);
    return this;
  }

  /**
   * Indicates coverages for the entire spatial unit, which means the coverage of all isochrones of a certain  range together for all spatial unit features. 
   * @return overallCoverage
   */
  @Valid 
  @Schema(name = "overallCoverage", description = "Indicates coverages for the entire spatial unit, which means the coverage of all isochrones of a certain  range together for all spatial unit features. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("overallCoverage")
  public List<@Valid OverallCoverageType> getOverallCoverage() {
    return overallCoverage;
  }

  public void setOverallCoverage(List<@Valid OverallCoverageType> overallCoverage) {
    this.overallCoverage = overallCoverage;
  }

  public IsochronePruneProcessResultType poiCoverage(List<@Valid PoiCoverageType> poiCoverage) {
    this.poiCoverage = poiCoverage;
    return this;
  }

  public IsochronePruneProcessResultType addPoiCoverageItem(PoiCoverageType poiCoverageItem) {
    if (this.poiCoverage == null) {
      this.poiCoverage = new ArrayList<>();
    }
    this.poiCoverage.add(poiCoverageItem);
    return this;
  }

  /**
   * Indicates single isochron coverages for each spatial unit. 
   * @return poiCoverage
   */
  @Valid 
  @Schema(name = "poiCoverage", description = "Indicates single isochron coverages for each spatial unit. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("poiCoverage")
  public List<@Valid PoiCoverageType> getPoiCoverage() {
    return poiCoverage;
  }

  public void setPoiCoverage(List<@Valid PoiCoverageType> poiCoverage) {
    this.poiCoverage = poiCoverage;
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
        Objects.equals(this.timeseries, isochronePruneProcessResultType.timeseries) &&
        Objects.equals(this.overallCoverage, isochronePruneProcessResultType.overallCoverage) &&
        Objects.equals(this.poiCoverage, isochronePruneProcessResultType.poiCoverage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(indicatorId, timeseries, overallCoverage, poiCoverage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IsochronePruneProcessResultType {\n");
    sb.append("    indicatorId: ").append(toIndentedString(indicatorId)).append("\n");
    sb.append("    timeseries: ").append(toIndentedString(timeseries)).append("\n");
    sb.append("    overallCoverage: ").append(toIndentedString(overallCoverage)).append("\n");
    sb.append("    poiCoverage: ").append(toIndentedString(poiCoverage)).append("\n");
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

