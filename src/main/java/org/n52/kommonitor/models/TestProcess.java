package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Process for testing the API.
 */

@Schema(name = "TestProcess", description = "Process for testing the API.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-26T20:33:13.177747+02:00[Europe/Budapest]")
public class TestProcess implements JobPOSTInputTypeParameters {

  @JsonProperty("test")
  private String test;

  public TestProcess test(String test) {
    this.test = test;
    return this;
  }

  /**
   * Get test
   * @return test
  */
  @NotNull 
  @Schema(name = "test", required = true)
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
    TestProcess testProcess = (TestProcess) o;
    return Objects.equals(this.test, testProcess.test);
  }

  @Override
  public int hashCode() {
    return Objects.hash(test);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TestProcess {\n");
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

