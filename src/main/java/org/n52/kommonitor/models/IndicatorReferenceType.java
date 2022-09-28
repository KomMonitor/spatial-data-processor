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
 * a reference to another indicator, e.g. a sub-indicator that is used to compute the main indicator
 */

@Schema(name = "IndicatorReferenceType", description = "a reference to another indicator, e.g. a sub-indicator that is used to compute the main indicator")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-28T15:53:09.022020300+02:00[Europe/Budapest]")
public class IndicatorReferenceType {

  @JsonProperty("referencedIndicatorDescription")
  private String referencedIndicatorDescription;

  @JsonProperty("referencedIndicatorId")
  private String referencedIndicatorId;

  @JsonProperty("referencedIndicatorName")
  private String referencedIndicatorName;

  public IndicatorReferenceType referencedIndicatorDescription(String referencedIndicatorDescription) {
    this.referencedIndicatorDescription = referencedIndicatorDescription;
    return this;
  }

  /**
   * a meaningful description of how the referenced indicator is related to the main indicator
   * @return referencedIndicatorDescription
  */
  @NotNull 
  @Schema(name = "referencedIndicatorDescription", description = "a meaningful description of how the referenced indicator is related to the main indicator", required = true)
  public String getReferencedIndicatorDescription() {
    return referencedIndicatorDescription;
  }

  public void setReferencedIndicatorDescription(String referencedIndicatorDescription) {
    this.referencedIndicatorDescription = referencedIndicatorDescription;
  }

  public IndicatorReferenceType referencedIndicatorId(String referencedIndicatorId) {
    this.referencedIndicatorId = referencedIndicatorId;
    return this;
  }

  /**
   * unique identifier of the referenced indicator
   * @return referencedIndicatorId
  */
  @NotNull 
  @Schema(name = "referencedIndicatorId", description = "unique identifier of the referenced indicator", required = true)
  public String getReferencedIndicatorId() {
    return referencedIndicatorId;
  }

  public void setReferencedIndicatorId(String referencedIndicatorId) {
    this.referencedIndicatorId = referencedIndicatorId;
  }

  public IndicatorReferenceType referencedIndicatorName(String referencedIndicatorName) {
    this.referencedIndicatorName = referencedIndicatorName;
    return this;
  }

  /**
   * the meaningful name of the referenced indicator
   * @return referencedIndicatorName
  */
  @NotNull 
  @Schema(name = "referencedIndicatorName", description = "the meaningful name of the referenced indicator", required = true)
  public String getReferencedIndicatorName() {
    return referencedIndicatorName;
  }

  public void setReferencedIndicatorName(String referencedIndicatorName) {
    this.referencedIndicatorName = referencedIndicatorName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndicatorReferenceType indicatorReferenceType = (IndicatorReferenceType) o;
    return Objects.equals(this.referencedIndicatorDescription, indicatorReferenceType.referencedIndicatorDescription) &&
        Objects.equals(this.referencedIndicatorId, indicatorReferenceType.referencedIndicatorId) &&
        Objects.equals(this.referencedIndicatorName, indicatorReferenceType.referencedIndicatorName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referencedIndicatorDescription, referencedIndicatorId, referencedIndicatorName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndicatorReferenceType {\n");
    sb.append("    referencedIndicatorDescription: ").append(toIndentedString(referencedIndicatorDescription)).append("\n");
    sb.append("    referencedIndicatorId: ").append(toIndentedString(referencedIndicatorId)).append("\n");
    sb.append("    referencedIndicatorName: ").append(toIndentedString(referencedIndicatorName)).append("\n");
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

