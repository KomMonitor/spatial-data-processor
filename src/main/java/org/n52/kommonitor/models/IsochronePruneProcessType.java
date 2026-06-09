package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Process that calculates overlap of spatial-units with isochrones to determine an accessibility rate for certain indicators. 
 */

@Schema(name = "IsochronePruneProcessType", description = "Process that calculates overlap of spatial-units with isochrones to determine an accessibility rate for certain indicators. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T22:37:08.978103200+02:00[Europe/Berlin]", comments = "Generator version: 7.18.0")
public class IsochronePruneProcessType implements ProcessType {

  private String name = "isochrone-prune";

  private @Nullable Object isochrones;

  private UUID spatialUnit;

  @Valid
  private List<UUID> indicator = new ArrayList<>();

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate date;

  /**
   * Method to use for weighting intersections of spatial units and isochrones:  * `simple` - No weighting will be applied, which means that intersection proportions are returned as is.  * `residential_areas` - Residential areas are additionally considered to calculate an intersection proportion. 
   */
  public enum WeightingEnum {
    SIMPLE("simple"),
    
    RESIDENTIAL_AREAS("residential_areas");

    private final String value;

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

  private @Nullable WeightingEnum weighting;

  public IsochronePruneProcessType() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public IsochronePruneProcessType(String name, UUID spatialUnit, List<UUID> indicator) {
    this.name = name;
    this.spatialUnit = spatialUnit;
    this.indicator = indicator;
  }

  public IsochronePruneProcessType name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Unique name of the process. Will be used to determine which process will be triggered server-side. 
   * @return name
   */
  @NotNull 
  @Schema(name = "name", description = "Unique name of the process. Will be used to determine which process will be triggered server-side. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public IsochronePruneProcessType isochrones(@Nullable Object isochrones) {
    this.isochrones = isochrones;
    return this;
  }

  /**
   * Generic type as container for GeoJSON FeatureCollections
   * @return isochrones
   */
  
  @Schema(name = "isochrones", description = "Generic type as container for GeoJSON FeatureCollections", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isochrones")
  public @Nullable Object getIsochrones() {
    return isochrones;
  }

  public void setIsochrones(@Nullable Object isochrones) {
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
  @Schema(name = "spatialUnit", description = "Unique identifier of the spatial unit. This ID will be used to fetch spatial unit data from the  KomMonitor DataManagement API. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("spatialUnit")
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
    if (this.indicator == null) {
      this.indicator = new ArrayList<>();
    }
    this.indicator.add(indicatorItem);
    return this;
  }

  /**
   * List of unique indicator identifiers. These IDs will be used to fetch indicator timeseries data from the  KomMonitor DataManagement API. 
   * @return indicator
   */
  @NotNull @Valid 
  @Schema(name = "indicator", description = "List of unique indicator identifiers. These IDs will be used to fetch indicator timeseries data from the  KomMonitor DataManagement API. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("indicator")
  public List<UUID> getIndicator() {
    return indicator;
  }

  public void setIndicator(List<UUID> indicator) {
    this.indicator = indicator;
  }

  public IsochronePruneProcessType date(@Nullable LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Defines the date for which indicator timeseries data will be fetched from DataManagement API. 
   * @return date
   */
  @Valid 
  @Schema(name = "date", description = "Defines the date for which indicator timeseries data will be fetched from DataManagement API. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date")
  public @Nullable LocalDate getDate() {
    return date;
  }

  public void setDate(@Nullable LocalDate date) {
    this.date = date;
  }

  public IsochronePruneProcessType weighting(@Nullable WeightingEnum weighting) {
    this.weighting = weighting;
    return this;
  }

  /**
   * Method to use for weighting intersections of spatial units and isochrones:  * `simple` - No weighting will be applied, which means that intersection proportions are returned as is.  * `residential_areas` - Residential areas are additionally considered to calculate an intersection proportion. 
   * @return weighting
   */
  
  @Schema(name = "weighting", description = "Method to use for weighting intersections of spatial units and isochrones:  * `simple` - No weighting will be applied, which means that intersection proportions are returned as is.  * `residential_areas` - Residential areas are additionally considered to calculate an intersection proportion. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("weighting")
  public @Nullable WeightingEnum getWeighting() {
    return weighting;
  }

  public void setWeighting(@Nullable WeightingEnum weighting) {
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

