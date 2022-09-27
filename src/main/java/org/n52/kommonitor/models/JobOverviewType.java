package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Description of a Job
 */

@Schema(name = "JobOverviewType", description = "Description of a Job")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-27T14:10:31.537835801+02:00[Europe/Amsterdam]")
public class JobOverviewType {

  @JsonProperty("id")
  private UUID id;

  @JsonProperty("process")
  private String process;

  @JsonProperty("timestamp")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime timestamp;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    QUEUED("queued"),
    
    RUNNING("running"),
    
    FINISHED("finished"),
    
    FAILED("failed");

    private String value;

    StatusEnum(String value) {
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
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("status")
  private StatusEnum status;

  @JsonProperty("result")
  private Object result;

  public JobOverviewType id(UUID id) {
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

  public JobOverviewType process(String process) {
    this.process = process;
    return this;
  }

  /**
   * Get process
   * @return process
  */
  @NotNull 
  @Schema(name = "process", required = true)
  public String getProcess() {
    return process;
  }

  public void setProcess(String process) {
    this.process = process;
  }

  public JobOverviewType timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
  */
  @NotNull @Valid 
  @Schema(name = "timestamp", required = true)
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public JobOverviewType status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @NotNull 
  @Schema(name = "status", required = true)
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public JobOverviewType result(Object result) {
    this.result = result;
    return this;
  }

  /**
   * Get result
   * @return result
  */
  
  @Schema(name = "result", required = false)
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
    JobOverviewType jobOverviewType = (JobOverviewType) o;
    return Objects.equals(this.id, jobOverviewType.id) &&
        Objects.equals(this.process, jobOverviewType.process) &&
        Objects.equals(this.timestamp, jobOverviewType.timestamp) &&
        Objects.equals(this.status, jobOverviewType.status) &&
        Objects.equals(this.result, jobOverviewType.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, process, timestamp, status, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JobOverviewType {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    process: ").append(toIndentedString(process)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

