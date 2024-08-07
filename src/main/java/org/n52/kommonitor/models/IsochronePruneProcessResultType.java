package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.n52.kommonitor.models.OverallCoverageType;
import org.n52.kommonitor.models.PoiCoverageType;
import org.n52.kommonitor.models.TimeseriesType;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-04T14:15:24.123563700+02:00[Europe/Berlin]")
public class IsochronePruneProcessResultType {

  @JsonProperty("indicatorId")
  private UUID indicatorId;

  @JsonProperty("timeseries")
  @Valid
  private List<TimeseriesType> timeseries = null;

  @JsonProperty("overallCoverage")
  @Valid
  private List<OverallCoverageType> overallCoverage = null;

  @JsonProperty("poiCoverage")
  @Valid
  private List<PoiCoverageType> poiCoverage = null;

  public IsochronePruneProcessResultType indicatorId(UUID indicatorId) {
    this.indicatorId = indicatorId;
    return this;
  }

  /**
   * Unique indicator identifier. Coverage values relates to this indicator. 
   * @return indicatorId
  */
  @NotNull @Valid 
  @Schema(name = "indicatorId", description = "Unique indicator identifier. Coverage values relates to this indicator. ", required = true)
  public UUID getIndicatorId() {
    return indicatorId;
  }

  public void setIndicatorId(UUID indicatorId) {
    this.indicatorId = indicatorId;
  }

  public IsochronePruneProcessResultType timeseries(List<TimeseriesType> timeseries) {
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
  @Schema(name = "timeseries", description = "The indicator timeseries values for which coverage fractions are calculated. ", required = false)
  public List<TimeseriesType> getTimeseries() {
    return timeseries;
  }

  public void setTimeseries(List<TimeseriesType> timeseries) {
    this.timeseries = timeseries;
  }

  public IsochronePruneProcessResultType overallCoverage(List<OverallCoverageType> overallCoverage) {
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
  @Schema(name = "overallCoverage", description = "Indicates coverages for the entire spatial unit, which means the coverage of all isochrones of a certain  range together for all spatial unit features. ", required = false)
  public List<OverallCoverageType> getOverallCoverage() {
    return overallCoverage;
  }

  public void setOverallCoverage(List<OverallCoverageType> overallCoverage) {
    this.overallCoverage = overallCoverage;
  }

  public IsochronePruneProcessResultType poiCoverage(List<PoiCoverageType> poiCoverage) {
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
  @Schema(name = "poiCoverage", description = "Indicates single isochron coverages for each spatial unit. ", required = false)
  public List<PoiCoverageType> getPoiCoverage() {
    return poiCoverage;
  }

  public void setPoiCoverage(List<PoiCoverageType> poiCoverage) {
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

