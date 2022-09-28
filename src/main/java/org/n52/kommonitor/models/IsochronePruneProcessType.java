package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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

@Schema(name = "IsochronePruneProcessType", description = "Process that calculates overlap of spatial-units with isochrones")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-28T15:53:08.183695100+02:00[Europe/Budapest]")
public class IsochronePruneProcessType implements ProcessType {

  @JsonProperty("name")
  private String name = "isochrone-prune";

  @JsonProperty("isochron")
  private Object isochron;

  @JsonProperty("spatialUnit")
  private Object spatialUnit;

  @JsonProperty("indicator")
  private Object indicator;

  @JsonProperty("configuration")
  private Object _configuration;

  public IsochronePruneProcessType name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", required = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public IsochronePruneProcessType isochron(Object isochron) {
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

  public IsochronePruneProcessType spatialUnit(Object spatialUnit) {
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

  public IsochronePruneProcessType indicator(Object indicator) {
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

  public IsochronePruneProcessType _configuration(Object _configuration) {
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
    IsochronePruneProcessType isochronePruneProcessType = (IsochronePruneProcessType) o;
    return Objects.equals(this.name, isochronePruneProcessType.name) &&
        Objects.equals(this.isochron, isochronePruneProcessType.isochron) &&
        Objects.equals(this.spatialUnit, isochronePruneProcessType.spatialUnit) &&
        Objects.equals(this.indicator, isochronePruneProcessType.indicator) &&
        Objects.equals(this._configuration, isochronePruneProcessType._configuration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, isochron, spatialUnit, indicator, _configuration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IsochronePruneProcessType {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

