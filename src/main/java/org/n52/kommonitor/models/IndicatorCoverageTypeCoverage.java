package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * IndicatorCoverageTypeCoverage
 */

@JsonTypeName("IndicatorCoverageType_coverage")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-20T19:18:43.546151100+02:00[Europe/Berlin]")
public class IndicatorCoverageTypeCoverage {

  @JsonProperty("absoluteCoverage")
  private Float absoluteCoverage;

  @JsonProperty("relativeCoverage")
  private Float relativeCoverage;

  public IndicatorCoverageTypeCoverage absoluteCoverage(Float absoluteCoverage) {
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

  public IndicatorCoverageTypeCoverage relativeCoverage(Float relativeCoverage) {
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
    IndicatorCoverageTypeCoverage indicatorCoverageTypeCoverage = (IndicatorCoverageTypeCoverage) o;
    return Objects.equals(this.absoluteCoverage, indicatorCoverageTypeCoverage.absoluteCoverage) &&
        Objects.equals(this.relativeCoverage, indicatorCoverageTypeCoverage.relativeCoverage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(absoluteCoverage, relativeCoverage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndicatorCoverageTypeCoverage {\n");
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

