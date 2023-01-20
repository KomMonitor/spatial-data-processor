package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
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
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-20T13:19:56.992510600+01:00[Europe/Berlin]")
public class IsochronePruneProcessType implements ProcessType {

  @JsonProperty("name")
  private String name = "isochrone-prune";

  @JsonProperty("isochron")
  private Object isochron;

  @JsonProperty("spatialUnit")
  private UUID spatialUnit;

  @JsonProperty("indicator")
  @Valid
  private List<UUID> indicator = new ArrayList<>();

  @JsonProperty("date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  /**
   * Method to use for weighting intersections of spatial units and isochrones. simple: No weighting will be applied, which means that intersection proportions are returned as is. residential_areas: Residential areas are additionally considered to calculate an intersection proportion.
   */
  public enum WeightingEnum {
    SIMPLE("simple"),
    
    RESIDENTIAL_AREAS("residential_areas");

    private String value;

    WeightingEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static WeightingEnum fromValue(String value) {
      for (WeightingEnum b : WeightingEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("weighting")
  private WeightingEnum weighting;

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

  public IsochronePruneProcessType spatialUnit(UUID spatialUnit) {
    this.spatialUnit = spatialUnit;
    return this;
  }

  /**
   * Get spatialUnit
   * @return spatialUnit
  */
  @NotNull @Valid 
  @Schema(name = "spatialUnit", required = true)
  public UUID getSpatialUnit() {
    return spatialUnit;
  }

  public void setSpatialUnit(UUID spatialUnit) {
    this.spatialUnit = spatialUnit;
  }

  public IsochronePruneProcessType indicator(List<UUID> indicator) {
    this.indicator = indicator;
    return this;
  }

  public IsochronePruneProcessType addIndicatorItem(UUID indicatorItem) {
    this.indicator.add(indicatorItem);
    return this;
  }

  /**
   * Get indicator
   * @return indicator
  */
  @NotNull @Valid 
  @Schema(name = "indicator", required = true)
  public List<UUID> getIndicator() {
    return indicator;
  }

  public void setIndicator(List<UUID> indicator) {
    this.indicator = indicator;
  }

  public IsochronePruneProcessType date(LocalDate date) {
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

  public IsochronePruneProcessType weighting(WeightingEnum weighting) {
    this.weighting = weighting;
    return this;
  }

  /**
   * Method to use for weighting intersections of spatial units and isochrones. simple: No weighting will be applied, which means that intersection proportions are returned as is. residential_areas: Residential areas are additionally considered to calculate an intersection proportion.
   * @return weighting
  */
  
  @Schema(name = "weighting", description = "Method to use for weighting intersections of spatial units and isochrones. simple: No weighting will be applied, which means that intersection proportions are returned as is. residential_areas: Residential areas are additionally considered to calculate an intersection proportion.", required = false)
  public WeightingEnum getWeighting() {
    return weighting;
  }

  public void setWeighting(WeightingEnum weighting) {
    this.weighting = weighting;
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
        Objects.equals(this.date, isochronePruneProcessType.date) &&
        Objects.equals(this.weighting, isochronePruneProcessType.weighting) &&
        Objects.equals(this._configuration, isochronePruneProcessType._configuration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, isochron, spatialUnit, indicator, date, weighting, _configuration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IsochronePruneProcessType {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    isochron: ").append(toIndentedString(isochron)).append("\n");
    sb.append("    spatialUnit: ").append(toIndentedString(spatialUnit)).append("\n");
    sb.append("    indicator: ").append(toIndentedString(indicator)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    weighting: ").append(toIndentedString(weighting)).append("\n");
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

