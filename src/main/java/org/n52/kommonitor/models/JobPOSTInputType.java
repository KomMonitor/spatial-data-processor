package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.n52.kommonitor.models.JobPOSTInputTypeParameters;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * POST type for a new job to be executed by the processor
 */

@Schema(name = "JobPOSTInputType", description = "POST type for a new job to be executed by the processor")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-26T20:33:13.177747+02:00[Europe/Budapest]")
public class JobPOSTInputType {

  @JsonProperty("process")
  private UUID process;

  @JsonProperty("parameters")
  private JobPOSTInputTypeParameters parameters;

  public JobPOSTInputType process(UUID process) {
    this.process = process;
    return this;
  }

  /**
   * Unique ID
   * @return process
  */
  @NotNull @Valid 
  @Schema(name = "process", description = "Unique ID", required = true)
  public UUID getProcess() {
    return process;
  }

  public void setProcess(UUID process) {
    this.process = process;
  }

  public JobPOSTInputType parameters(JobPOSTInputTypeParameters parameters) {
    this.parameters = parameters;
    return this;
  }

  /**
   * Get parameters
   * @return parameters
  */
  @NotNull @Valid 
  @Schema(name = "parameters", required = true)
  public JobPOSTInputTypeParameters getParameters() {
    return parameters;
  }

  public void setParameters(JobPOSTInputTypeParameters parameters) {
    this.parameters = parameters;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JobPOSTInputType jobPOSTInputType = (JobPOSTInputType) o;
    return Objects.equals(this.process, jobPOSTInputType.process) &&
        Objects.equals(this.parameters, jobPOSTInputType.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(process, parameters);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JobPOSTInputType {\n");
    sb.append("    process: ").append(toIndentedString(process)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
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

