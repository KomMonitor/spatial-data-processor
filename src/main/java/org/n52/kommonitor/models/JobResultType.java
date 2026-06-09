package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * JobResultType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T22:37:08.978103200+02:00[Europe/Berlin]", comments = "Generator version: 7.18.0")
public class JobResultType {

  private UUID id;

  private Object result;

  public JobResultType() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public JobResultType(UUID id, Object result) {
    this.id = id;
    this.result = result;
  }

  public JobResultType id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Unique ID
   * @return id
   */
  @NotNull @Valid 
  @Schema(name = "id", description = "Unique ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
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
   * Result of the process execution. The result is a JSON object whose schema depends on the underlying process. Each process has its own result schema. 
   * @return result
   */
  @NotNull 
  @Schema(name = "result", description = "Result of the process execution. The result is a JSON object whose schema depends on the underlying process. Each process has its own result schema. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("result")
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

