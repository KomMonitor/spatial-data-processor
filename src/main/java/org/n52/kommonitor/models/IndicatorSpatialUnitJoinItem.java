package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * IndicatorSpatialUnitJoinItem
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-02-03T01:17:35.372611508+01:00[Europe/Berlin]")
public class IndicatorSpatialUnitJoinItem {

  @JsonProperty("allowedRoles")
  @Valid
  private List<String> allowedRoles = null;

  @JsonProperty("spatialUnitId")
  private String spatialUnitId;

  @JsonProperty("spatialUnitName")
  private String spatialUnitName;

  @JsonProperty("userPermissions")
  @Valid
  private List<String> userPermissions = null;

  public IndicatorSpatialUnitJoinItem allowedRoles(List<String> allowedRoles) {
    this.allowedRoles = allowedRoles;
    return this;
  }

  public IndicatorSpatialUnitJoinItem addAllowedRolesItem(String allowedRolesItem) {
    if (this.allowedRoles == null) {
      this.allowedRoles = new ArrayList<>();
    }
    this.allowedRoles.add(allowedRolesItem);
    return this;
  }

  /**
   * list of role identifiers that have read access rights for this dataset
   * @return allowedRoles
  */
  
  @Schema(name = "allowedRoles", description = "list of role identifiers that have read access rights for this dataset", required = false)
  public List<String> getAllowedRoles() {
    return allowedRoles;
  }

  public void setAllowedRoles(List<String> allowedRoles) {
    this.allowedRoles = allowedRoles;
  }

  public IndicatorSpatialUnitJoinItem spatialUnitId(String spatialUnitId) {
    this.spatialUnitId = spatialUnitId;
    return this;
  }

  /**
   * ID of the applicable spatial unit
   * @return spatialUnitId
  */
  @NotNull 
  @Schema(name = "spatialUnitId", description = "ID of the applicable spatial unit", required = true)
  public String getSpatialUnitId() {
    return spatialUnitId;
  }

  public void setSpatialUnitId(String spatialUnitId) {
    this.spatialUnitId = spatialUnitId;
  }

  public IndicatorSpatialUnitJoinItem spatialUnitName(String spatialUnitName) {
    this.spatialUnitName = spatialUnitName;
    return this;
  }

  /**
   * name of the applicable spatial unit
   * @return spatialUnitName
  */
  @NotNull 
  @Schema(name = "spatialUnitName", description = "name of the applicable spatial unit", required = true)
  public String getSpatialUnitName() {
    return spatialUnitName;
  }

  public void setSpatialUnitName(String spatialUnitName) {
    this.spatialUnitName = spatialUnitName;
  }

  public IndicatorSpatialUnitJoinItem userPermissions(List<String> userPermissions) {
    this.userPermissions = userPermissions;
    return this;
  }

  public IndicatorSpatialUnitJoinItem addUserPermissionsItem(String userPermissionsItem) {
    if (this.userPermissions == null) {
      this.userPermissions = new ArrayList<>();
    }
    this.userPermissions.add(userPermissionsItem);
    return this;
  }

  /**
   * Permission Level
   * @return userPermissions
  */
  
  @Schema(name = "userPermissions", description = "Permission Level", required = false)
  public List<String> getUserPermissions() {
    return userPermissions;
  }

  public void setUserPermissions(List<String> userPermissions) {
    this.userPermissions = userPermissions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IndicatorSpatialUnitJoinItem indicatorSpatialUnitJoinItem = (IndicatorSpatialUnitJoinItem) o;
    return Objects.equals(this.allowedRoles, indicatorSpatialUnitJoinItem.allowedRoles) &&
        Objects.equals(this.spatialUnitId, indicatorSpatialUnitJoinItem.spatialUnitId) &&
        Objects.equals(this.spatialUnitName, indicatorSpatialUnitJoinItem.spatialUnitName) &&
        Objects.equals(this.userPermissions, indicatorSpatialUnitJoinItem.userPermissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(allowedRoles, spatialUnitId, spatialUnitName, userPermissions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IndicatorSpatialUnitJoinItem {\n");
    sb.append("    allowedRoles: ").append(toIndentedString(allowedRoles)).append("\n");
    sb.append("    spatialUnitId: ").append(toIndentedString(spatialUnitId)).append("\n");
    sb.append("    spatialUnitName: ").append(toIndentedString(spatialUnitName)).append("\n");
    sb.append("    userPermissions: ").append(toIndentedString(userPermissions)).append("\n");
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

