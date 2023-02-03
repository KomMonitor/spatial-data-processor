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
 * Process that calculates overlap of spatial-units with isochrones to determine an accessibility rate for certain indicators. 
 */

@Schema(name = "IsochronePruneProcessType", description = "Process that calculates overlap of spatial-units with isochrones to determine an accessibility rate for certain indicators. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-02-03T01:17:34.621019675+01:00[Europe/Berlin]")
public class IsochronePruneProcessType implements ProcessType {

  @JsonProperty("name")
  private String name = "isochrone-prune";

  @JsonProperty("isochrones")
  private Object isochrones;

  @JsonProperty("spatialUnit")
  private UUID spatialUnit;

  @JsonProperty("indicator")
  @Valid
  private List<UUID> indicator = new ArrayList<>();

  @JsonProperty("date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  /**
   * Method to use for weighting intersections of spatial units and isochrones:  * `simple` - No weighting will be applied, which means that intersection proportions are returned as is.  * `residential_areas` - Residential areas are additionally considered to calculate an intersection proportion. 
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

  public IsochronePruneProcessType name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Unique name of the process. Will be used to determine which process will be triggered server-side. 
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "Unique name of the process. Will be used to determine which process will be triggered server-side. ", required = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public IsochronePruneProcessType isochrones(Object isochrones) {
    this.isochrones = isochrones;
    return this;
  }

  /**
   * Generic type as container for GeoJSON FeatureCollections
   * @return isochrones
  */
  
  @Schema(name = "isochrones", description = "Generic type as container for GeoJSON FeatureCollections", required = false)
  public Object getIsochrones() {
    return isochrones;
  }

  public void setIsochrones(Object isochrones) {
    this.isochrones = isochrones;
  }

  public IsochronePruneProcessType spatialUnit(UUID spatialUnit) {
    this.spatialUnit = spatialUnit;
    return this;
  }

  /**
   * Unique identifier of the spatial unit. This ID will be used to fetch spatial unit data from the  KomMonitor DataManagement API. 
   * @return spatialUnit
  */
  @NotNull @Valid 
  @Schema(name = "spatialUnit", description = "Unique identifier of the spatial unit. This ID will be used to fetch spatial unit data from the  KomMonitor DataManagement API. ", required = true)
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
   * List of unique indicator identifiers. These IDs will be used to fetch indicator timeseries data from the  KomMonitor DataManagement API. 
   * @return indicator
  */
  @NotNull @Valid 
  @Schema(name = "indicator", description = "List of unique indicator identifiers. These IDs will be used to fetch indicator timeseries data from the  KomMonitor DataManagement API. ", required = true)
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
   * Defines the date for which indicator timeseries data will be fetched from DataManagement API. 
   * @return date
  */
  @Valid 
  @Schema(name = "date", description = "Defines the date for which indicator timeseries data will be fetched from DataManagement API. ", required = false)
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
   * Method to use for weighting intersections of spatial units and isochrones:  * `simple` - No weighting will be applied, which means that intersection proportions are returned as is.  * `residential_areas` - Residential areas are additionally considered to calculate an intersection proportion. 
   * @return weighting
  */
  
  @Schema(name = "weighting", description = "Method to use for weighting intersections of spatial units and isochrones:  * `simple` - No weighting will be applied, which means that intersection proportions are returned as is.  * `residential_areas` - Residential areas are additionally considered to calculate an intersection proportion. ", required = false)
  public WeightingEnum getWeighting() {
    return weighting;
  }

  public void setWeighting(WeightingEnum weighting) {
    this.weighting = weighting;
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
        Objects.equals(this.isochrones, isochronePruneProcessType.isochrones) &&
        Objects.equals(this.spatialUnit, isochronePruneProcessType.spatialUnit) &&
        Objects.equals(this.indicator, isochronePruneProcessType.indicator) &&
        Objects.equals(this.date, isochronePruneProcessType.date) &&
        Objects.equals(this.weighting, isochronePruneProcessType.weighting);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, isochrones, spatialUnit, indicator, date, weighting);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IsochronePruneProcessType {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    isochrones: ").append(toIndentedString(isochrones)).append("\n");
    sb.append("    spatialUnit: ").append(toIndentedString(spatialUnit)).append("\n");
    sb.append("    indicator: ").append(toIndentedString(indicator)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    weighting: ").append(toIndentedString(weighting)).append("\n");
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

