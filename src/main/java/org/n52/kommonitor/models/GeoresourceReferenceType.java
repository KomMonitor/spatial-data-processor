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
 * a reference to georesource, e.g. a resource that is used to compute the main indicator
 */

@Schema(name = "GeoresourceReferenceType", description = "a reference to georesource, e.g. a resource that is used to compute the main indicator")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-28T15:53:09.022020300+02:00[Europe/Budapest]")
public class GeoresourceReferenceType {

  @JsonProperty("referencedGeoresourceDescription")
  private String referencedGeoresourceDescription;

  @JsonProperty("referencedGeoresourceId")
  private String referencedGeoresourceId;

  @JsonProperty("referencedGeoresourceName")
  private String referencedGeoresourceName;

  public GeoresourceReferenceType referencedGeoresourceDescription(String referencedGeoresourceDescription) {
    this.referencedGeoresourceDescription = referencedGeoresourceDescription;
    return this;
  }

  /**
   * a meaningful description of how the referenced georesource is related to the main indicator
   * @return referencedGeoresourceDescription
  */
  @NotNull 
  @Schema(name = "referencedGeoresourceDescription", description = "a meaningful description of how the referenced georesource is related to the main indicator", required = true)
  public String getReferencedGeoresourceDescription() {
    return referencedGeoresourceDescription;
  }

  public void setReferencedGeoresourceDescription(String referencedGeoresourceDescription) {
    this.referencedGeoresourceDescription = referencedGeoresourceDescription;
  }

  public GeoresourceReferenceType referencedGeoresourceId(String referencedGeoresourceId) {
    this.referencedGeoresourceId = referencedGeoresourceId;
    return this;
  }

  /**
   * unique identifier of the referenced georesource
   * @return referencedGeoresourceId
  */
  @NotNull 
  @Schema(name = "referencedGeoresourceId", description = "unique identifier of the referenced georesource", required = true)
  public String getReferencedGeoresourceId() {
    return referencedGeoresourceId;
  }

  public void setReferencedGeoresourceId(String referencedGeoresourceId) {
    this.referencedGeoresourceId = referencedGeoresourceId;
  }

  public GeoresourceReferenceType referencedGeoresourceName(String referencedGeoresourceName) {
    this.referencedGeoresourceName = referencedGeoresourceName;
    return this;
  }

  /**
   * the meaningful name of the referenced georesource
   * @return referencedGeoresourceName
  */
  @NotNull 
  @Schema(name = "referencedGeoresourceName", description = "the meaningful name of the referenced georesource", required = true)
  public String getReferencedGeoresourceName() {
    return referencedGeoresourceName;
  }

  public void setReferencedGeoresourceName(String referencedGeoresourceName) {
    this.referencedGeoresourceName = referencedGeoresourceName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeoresourceReferenceType georesourceReferenceType = (GeoresourceReferenceType) o;
    return Objects.equals(this.referencedGeoresourceDescription, georesourceReferenceType.referencedGeoresourceDescription) &&
        Objects.equals(this.referencedGeoresourceId, georesourceReferenceType.referencedGeoresourceId) &&
        Objects.equals(this.referencedGeoresourceName, georesourceReferenceType.referencedGeoresourceName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referencedGeoresourceDescription, referencedGeoresourceId, referencedGeoresourceName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeoresourceReferenceType {\n");
    sb.append("    referencedGeoresourceDescription: ").append(toIndentedString(referencedGeoresourceDescription)).append("\n");
    sb.append("    referencedGeoresourceId: ").append(toIndentedString(referencedGeoresourceId)).append("\n");
    sb.append("    referencedGeoresourceName: ").append(toIndentedString(referencedGeoresourceName)).append("\n");
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

