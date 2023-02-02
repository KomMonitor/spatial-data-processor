package org.n52.kommonitor.models;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.n52.kommonitor.models.IsochronePruneProcessType;
import org.n52.kommonitor.models.TestProcessType;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;


@JsonIgnoreProperties(
  value = "name", // ignore manually set name, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the name to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "name", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = IsochronePruneProcessType.class, name = "IsochronePruneProcessType"),
  @JsonSubTypes.Type(value = TestProcessType.class, name = "TestProcessType"),
  @JsonSubTypes.Type(value = IsochronePruneProcessType.class, name = "isochrone-prune"),
  @JsonSubTypes.Type(value = TestProcessType.class, name = "test")
})

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-02-03T00:29:29.885216558+01:00[Europe/Berlin]")
public interface ProcessType {
    public String getName();
}
