package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * IndicatorCoverageValueType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-02-03T00:29:29.885216558+01:00[Europe/Berlin]")
public class IndicatorCoverageValueType {

  @JsonProperty("date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  @JsonProperty("absoluteCoverage")
  private Float absoluteCoverage;

  @JsonProperty("relativeCoverage")
  private Float relativeCoverage;

  public IndicatorCoverageValueType date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  */
  @Valid 
  @Schema(name = "date", required = false)
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public IndicatorCoverageValueType absoluteCoverage(Float absoluteCoverage) {
    this.absoluteCoverage = absoluteCoverage;
    return this;
  }

  /**
   * Get absoluteCoverage
   * @return absoluteCoverage
  */
  
  @Schema(name = "absoluteCoverage", required = false)
  public Float getAbsoluteCoverage() {
    return absoluteCoverage;
  }

  public void setAbsoluteCoverage(Float absoluteCoverage) {
    this.absoluteCoverage = absoluteCoverage;
  }

  public IndicatorCoverageValueType relativeCoverage(Float relativeCoverage) {
    this.relativeCoverage = relativeCoverage;
    return this;
  }

  /**
   * Get relativeCoverage
   * @return relativeCoverage
  */
  
  @Schema(name = "relativeCoverage", required = false)
  public Float getRelativeCoverage() {
    return relativeCoverage;
  }

  public void setRelativeCoverage(Float relativeCoverage) {
    this.relativeCoverage = relativeCoverage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndicatorCoverageValueType indicatorCoverageValueType = (IndicatorCoverageValueType) o;
    return Objects.equals(this.date, indicatorCoverageValueType.date) &&
        Objects.equals(this.absoluteCoverage, indicatorCoverageValueType.absoluteCoverage) &&
        Objects.equals(this.relativeCoverage, indicatorCoverageValueType.relativeCoverage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, absoluteCoverage, relativeCoverage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndicatorCoverageValueType {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    absoluteCoverage: ").append(toIndentedString(absoluteCoverage)).append("\n");
    sb.append("    relativeCoverage: ").append(toIndentedString(relativeCoverage)).append("\n");
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

