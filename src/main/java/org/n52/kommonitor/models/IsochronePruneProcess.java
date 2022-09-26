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
 * Process that calculates overlap of spatial-units with isochrones
 */

@Schema(name = "IsochronePruneProcess", description = "Process that calculates overlap of spatial-units with isochrones")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-26T20:33:13.177747+02:00[Europe/Budapest]")
public class IsochronePruneProcess implements JobPOSTInputTypeParameters {

  @JsonProperty("isochron")
  private Object isochron;

  @JsonProperty("spatialUnit")
  private Object spatialUnit;

  @JsonProperty("indicator")
  private Object indicator;

  @JsonProperty("configuration")
  private Object _configuration;

  public IsochronePruneProcess isochron(Object isochron) {
    this.isochron = isochron;
    return this;
  }

  /**
   * GeoJSON FeatureCollection
   * @return isochron
  */
  @NotNull 
  @Schema(name = "isochron", description = "GeoJSON FeatureCollection", required = true)
  public Object getIsochron() {
    return isochron;
  }

  public void setIsochron(Object isochron) {
    this.isochron = isochron;
  }

  public IsochronePruneProcess spatialUnit(Object spatialUnit) {
    this.spatialUnit = spatialUnit;
    return this;
  }

  /**
   * Get spatialUnit
   * @return spatialUnit
  */
  @NotNull 
  @Schema(name = "spatialUnit", required = true)
  public Object getSpatialUnit() {
    return spatialUnit;
  }

  public void setSpatialUnit(Object spatialUnit) {
    this.spatialUnit = spatialUnit;
  }

  public IsochronePruneProcess indicator(Object indicator) {
    this.indicator = indicator;
    return this;
  }

  /**
   * Get indicator
   * @return indicator
  */
  @NotNull 
  @Schema(name = "indicator", required = true)
  public Object getIndicator() {
    return indicator;
  }

  public void setIndicator(Object indicator) {
    this.indicator = indicator;
  }

  public IsochronePruneProcess _configuration(Object _configuration) {
    this._configuration = _configuration;
    return this;
  }

  /**
   * Additional configuration properties for this process
   * @return _configuration
  */
  @NotNull 
  @Schema(name = "configuration", description = "Additional configuration properties for this process", required = true)
  public Object getConfiguration() {
    return _configuration;
  }

  public void setConfiguration(Object _configuration) {
    this._configuration = _configuration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IsochronePruneProcess isochronePruneProcess = (IsochronePruneProcess) o;
    return Objects.equals(this.isochron, isochronePruneProcess.isochron) &&
        Objects.equals(this.spatialUnit, isochronePruneProcess.spatialUnit) &&
        Objects.equals(this.indicator, isochronePruneProcess.indicator) &&
        Objects.equals(this._configuration, isochronePruneProcess._configuration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isochron, spatialUnit, indicator, _configuration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IsochronePruneProcess {\n");
    sb.append("    isochron: ").append(toIndentedString(isochron)).append("\n");
    sb.append("    spatialUnit: ").append(toIndentedString(spatialUnit)).append("\n");
    sb.append("    indicator: ").append(toIndentedString(indicator)).append("\n");
    sb.append("    _configuration: ").append(toIndentedString(_configuration)).append("\n");
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

