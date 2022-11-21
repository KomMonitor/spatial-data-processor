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
 * OverallCoverageType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-07T17:14:05.706474+01:00[Europe/Berlin]")
public class OverallCoverageType {

  @JsonProperty("range")
  private Float range;

  @JsonProperty("coverage")
  @Valid
  private List<IndicatorCoverageValueType> coverage = null;

  public OverallCoverageType range(Float range) {
    this.range = range;
    return this;
  }

  /**
   * Get range
   * @return range
  */
  @NotNull 
  @Schema(name = "range", required = true)
  public Float getRange() {
    return range;
  }

  public void setRange(Float range) {
    this.range = range;
  }

  public OverallCoverageType coverage(List<IndicatorCoverageValueType> coverage) {
    this.coverage = coverage;
    return this;
  }

  public OverallCoverageType addCoverageItem(IndicatorCoverageValueType coverageItem) {
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
    OverallCoverageType overallCoverageType = (OverallCoverageType) o;
    return Objects.equals(this.range, overallCoverageType.range) &&
        Objects.equals(this.coverage, overallCoverageType.coverage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(range, coverage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OverallCoverageType {\n");
    sb.append("    range: ").append(toIndentedString(range)).append("\n");
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

