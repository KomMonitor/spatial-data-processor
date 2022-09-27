package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * definition of the period of validity of a certain dataset
 */

@Schema(name = "PeriodOfValidityType", description = "definition of the period of validity of a certain dataset")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-27T18:34:42.190931576+02:00[Europe/Amsterdam]")
public class PeriodOfValidityType {

  @JsonProperty("endDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate endDate;

  @JsonProperty("startDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate startDate;

  public PeriodOfValidityType endDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * an optional timestamp representing the ending date according to ISO 8601 (e.g. 2018-01-30). The parameter can be omitted, if the end date is unknown.
   * @return endDate
  */
  @Valid 
  @Schema(name = "endDate", description = "an optional timestamp representing the ending date according to ISO 8601 (e.g. 2018-01-30). The parameter can be omitted, if the end date is unknown.", required = false)
  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public PeriodOfValidityType startDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * a timestamp representing the starting date according to ISO 8601 (e.g. 2018-01-30)
   * @return startDate
  */
  @NotNull @Valid 
  @Schema(name = "startDate", description = "a timestamp representing the starting date according to ISO 8601 (e.g. 2018-01-30)", required = true)
  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PeriodOfValidityType periodOfValidityType = (PeriodOfValidityType) o;
    return Objects.equals(this.endDate, periodOfValidityType.endDate) &&
        Objects.equals(this.startDate, periodOfValidityType.startDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(endDate, startDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PeriodOfValidityType {\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
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

