package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DefaultClassificationMappingItemType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T22:37:11.343634300+02:00[Europe/Berlin]", comments = "Generator version: 7.18.0")
public class DefaultClassificationMappingItemType {

  private String spatialUnitId;

  @Valid
  private List<Float> breaks = new ArrayList<>();

  public DefaultClassificationMappingItemType() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DefaultClassificationMappingItemType(String spatialUnitId, List<Float> breaks) {
    this.spatialUnitId = spatialUnitId;
    this.breaks = breaks;
  }

  public DefaultClassificationMappingItemType spatialUnitId(String spatialUnitId) {
    this.spatialUnitId = spatialUnitId;
    return this;
  }

  /**
   * spatial unit id for manual classification
   * @return spatialUnitId
   */
  @NotNull 
  @Schema(name = "spatialUnitId", description = "spatial unit id for manual classification", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("spatialUnitId")
  public String getSpatialUnitId() {
    return spatialUnitId;
  }

  public void setSpatialUnitId(String spatialUnitId) {
    this.spatialUnitId = spatialUnitId;
  }

  public DefaultClassificationMappingItemType breaks(List<Float> breaks) {
    this.breaks = breaks;
    return this;
  }

  public DefaultClassificationMappingItemType addBreaksItem(Float breaksItem) {
    if (this.breaks == null) {
      this.breaks = new ArrayList<>();
    }
    this.breaks.add(breaksItem);
    return this;
  }

  /**
   * array of numeric break values
   * @return breaks
   */
  @NotNull 
  @Schema(name = "breaks", description = "array of numeric break values", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("breaks")
  public List<Float> getBreaks() {
    return breaks;
  }

  public void setBreaks(List<Float> breaks) {
    this.breaks = breaks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DefaultClassificationMappingItemType defaultClassificationMappingItemType = (DefaultClassificationMappingItemType) o;
    return Objects.equals(this.spatialUnitId, defaultClassificationMappingItemType.spatialUnitId) &&
        Objects.equals(this.breaks, defaultClassificationMappingItemType.breaks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(spatialUnitId, breaks);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DefaultClassificationMappingItemType {\n");
    sb.append("    spatialUnitId: ").append(toIndentedString(spatialUnitId)).append("\n");
    sb.append("    breaks: ").append(toIndentedString(breaks)).append("\n");
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

