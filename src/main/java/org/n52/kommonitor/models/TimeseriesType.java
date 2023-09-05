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
 * TimeseriesType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-05T08:16:59.005638400+02:00[Europe/Berlin]")
public class TimeseriesType {

  @JsonProperty("date")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  @JsonProperty("value")
  private Float value;

  public TimeseriesType date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Indicates for which indicator timeseries date the indicator value relates to. 
   * @return date
  */
  @Valid 
  @Schema(name = "date", description = "Indicates for which indicator timeseries date the indicator value relates to. ", required = false)
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public TimeseriesType value(Float value) {
    this.value = value;
    return this;
  }

  /**
   * Indicator value.         
   * @return value
  */
  
  @Schema(name = "value", description = "Indicator value.         ", required = false)
  public Float getValue() {
    return value;
  }

  public void setValue(Float value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeseriesType timeseriesType = (TimeseriesType) o;
    return Objects.equals(this.date, timeseriesType.date) &&
        Objects.equals(this.value, timeseriesType.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeseriesType {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

