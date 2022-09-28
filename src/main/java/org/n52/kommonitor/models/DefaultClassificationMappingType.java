package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.n52.kommonitor.models.DefaultClassificationMappingItemType;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * DefaultClassificationMappingType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-28T15:53:09.022020300+02:00[Europe/Budapest]")
public class DefaultClassificationMappingType {

  @JsonProperty("colorBrewerSchemeName")
  private String colorBrewerSchemeName;

  @JsonProperty("items")
  @Valid
  private List<DefaultClassificationMappingItemType> items = new ArrayList<>();

  public DefaultClassificationMappingType colorBrewerSchemeName(String colorBrewerSchemeName) {
    this.colorBrewerSchemeName = colorBrewerSchemeName;
    return this;
  }

  /**
   * the name of the colorBrewer color scheme jused to define the colors for classification (see project http://colorbrewer2.org/#type=sequential&scheme=BuGn&n=3 for colorSchemes). Set to 'INDIVIDUAL' if colors are set arbitrarily.
   * @return colorBrewerSchemeName
  */
  @NotNull 
  @Schema(name = "colorBrewerSchemeName", description = "the name of the colorBrewer color scheme jused to define the colors for classification (see project http://colorbrewer2.org/#type=sequential&scheme=BuGn&n=3 for colorSchemes). Set to 'INDIVIDUAL' if colors are set arbitrarily.", required = true)
  public String getColorBrewerSchemeName() {
    return colorBrewerSchemeName;
  }

  public void setColorBrewerSchemeName(String colorBrewerSchemeName) {
    this.colorBrewerSchemeName = colorBrewerSchemeName;
  }

  public DefaultClassificationMappingType items(List<DefaultClassificationMappingItemType> items) {
    this.items = items;
    return this;
  }

  public DefaultClassificationMappingType addItemsItem(DefaultClassificationMappingItemType itemsItem) {
    this.items.add(itemsItem);
    return this;
  }

  /**
   * array of classification mapping items. The order of the items corresponds to indicator value intervals from low to high. The number of items represents the number of classes. In combination they represent the default classification and mapping to custom rating of the indicator values
   * @return items
  */
  @NotNull @Valid 
  @Schema(name = "items", description = "array of classification mapping items. The order of the items corresponds to indicator value intervals from low to high. The number of items represents the number of classes. In combination they represent the default classification and mapping to custom rating of the indicator values", required = true)
  public List<DefaultClassificationMappingItemType> getItems() {
    return items;
  }

  public void setItems(List<DefaultClassificationMappingItemType> items) {
    this.items = items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DefaultClassificationMappingType defaultClassificationMappingType = (DefaultClassificationMappingType) o;
    return Objects.equals(this.colorBrewerSchemeName, defaultClassificationMappingType.colorBrewerSchemeName) &&
        Objects.equals(this.items, defaultClassificationMappingType.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(colorBrewerSchemeName, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DefaultClassificationMappingType {\n");
    sb.append("    colorBrewerSchemeName: ").append(toIndentedString(colorBrewerSchemeName)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

