package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * JobResultType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-10-20T10:40:58.862087900+02:00[Europe/Berlin]")
public class JobResultType {

  @JsonProperty("id")
  private UUID id;

  @JsonProperty("result")
  private Object result;

  public JobResultType id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Unique ID
   * @return id
  */
  @NotNull @Valid 
  @Schema(name = "id", description = "Unique ID", required = true)
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public JobResultType result(Object result) {
    this.result = result;
    return this;
  }

  /**
   * The result is a JSON object whose schema depends on the underlying process. Each process has its own result schema. 
   * @return result
  */
  @NotNull 
  @Schema(name = "result", description = "The result is a JSON object whose schema depends on the underlying process. Each process has its own result schema. ", required = true)
  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JobResultType jobResultType = (JobResultType) o;
    return Objects.equals(this.id, jobResultType.id) &&
        Objects.equals(this.result, jobResultType.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JobResultType {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
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

