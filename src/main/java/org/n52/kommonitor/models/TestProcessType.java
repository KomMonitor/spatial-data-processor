package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Process for testing the API.
 */

@Schema(name = "TestProcessType", description = "Process for testing the API.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-06-08T22:37:08.978103200+02:00[Europe/Berlin]", comments = "Generator version: 7.18.0")
public class TestProcessType implements ProcessType {

  private String name = "test";

  private String test;

  public TestProcessType() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TestProcessType(String name, String test) {
    this.name = name;
    this.test = test;
  }

  public TestProcessType name(String name) {
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

  public TestProcessType test(String test) {
    this.test = test;
    return this;
  }

  /**
   * Just a dummy test parameter. 
   * @return test
   */
  @NotNull 
  @Schema(name = "test", description = "Just a dummy test parameter. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("test")
  public String getTest() {
    return test;
  }

  public void setTest(String test) {
    this.test = test;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestProcessType testProcessType = (TestProcessType) o;
    return Objects.equals(this.name, testProcessType.name) &&
        Objects.equals(this.test, testProcessType.test);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, test);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TestProcessType {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    test: ").append(toIndentedString(test)).append("\n");
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

