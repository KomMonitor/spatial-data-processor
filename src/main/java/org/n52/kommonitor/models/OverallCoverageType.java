package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.n52.kommonitor.models.IndicatorCoverageValueType;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * OverallCoverageType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T22:37:08.978103200+02:00[Europe/Berlin]", comments = "Generator version: 7.18.0")
public class OverallCoverageType {

  private Float range;

  @Valid
  private List<@Valid IndicatorCoverageValueType> coverage = new ArrayList<>();

  public OverallCoverageType() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public OverallCoverageType(Float range) {
    this.range = range;
  }

  public OverallCoverageType range(Float range) {
    this.range = range;
    return this;
  }

  /**
   * Indicates the isochron range that relates to the indicator coverage values. 
   * @return range
   */
  @NotNull 
  @Schema(name = "range", description = "Indicates the isochron range that relates to the indicator coverage values. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("range")
  public Float getRange() {
    return range;
  }

  public void setRange(Float range) {
    this.range = range;
  }

  public OverallCoverageType coverage(List<@Valid IndicatorCoverageValueType> coverage) {
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
   * The coverage values of an isochron for a certain indicator. 
   * @return coverage
   */
  @Valid 
  @Schema(name = "coverage", description = "The coverage values of an isochron for a certain indicator. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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

