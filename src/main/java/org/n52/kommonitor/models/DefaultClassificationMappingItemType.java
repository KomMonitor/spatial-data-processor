package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * DefaultClassificationMappingItemType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-02-03T01:17:35.372611508+01:00[Europe/Berlin]")
public class DefaultClassificationMappingItemType {

  @JsonProperty("defaultColorAsHex")
  private String defaultColorAsHex;

  @JsonProperty("defaultCustomRating")
  private String defaultCustomRating;

  public DefaultClassificationMappingItemType defaultColorAsHex(String defaultColorAsHex) {
    this.defaultColorAsHex = defaultColorAsHex;
    return this;
  }

  /**
   * the default color for the specified value interval as hex string inclusive leading #, i.e. '#ffffff'
   * @return defaultColorAsHex
  */
  @NotNull 
  @Schema(name = "defaultColorAsHex", description = "the default color for the specified value interval as hex string inclusive leading #, i.e. '#ffffff'", required = true)
  public String getDefaultColorAsHex() {
    return defaultColorAsHex;
  }

  public void setDefaultColorAsHex(String defaultColorAsHex) {
    this.defaultColorAsHex = defaultColorAsHex;
  }

  public DefaultClassificationMappingItemType defaultCustomRating(String defaultCustomRating) {
    this.defaultCustomRating = defaultCustomRating;
    return this;
  }

  /**
   * the default custom rating string for the specified value interval, i.e. 'very high'/'very low' or 'good'/'bad'
   * @return defaultCustomRating
  */
  @NotNull 
  @Schema(name = "defaultCustomRating", description = "the default custom rating string for the specified value interval, i.e. 'very high'/'very low' or 'good'/'bad'", required = true)
  public String getDefaultCustomRating() {
    return defaultCustomRating;
  }

  public void setDefaultCustomRating(String defaultCustomRating) {
    this.defaultCustomRating = defaultCustomRating;
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
    return Objects.equals(this.defaultColorAsHex, defaultClassificationMappingItemType.defaultColorAsHex) &&
        Objects.equals(this.defaultCustomRating, defaultClassificationMappingItemType.defaultCustomRating);
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultColorAsHex, defaultCustomRating);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DefaultClassificationMappingItemType {\n");
    sb.append("    defaultColorAsHex: ").append(toIndentedString(defaultColorAsHex)).append("\n");
    sb.append("    defaultCustomRating: ").append(toIndentedString(defaultCustomRating)).append("\n");
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

