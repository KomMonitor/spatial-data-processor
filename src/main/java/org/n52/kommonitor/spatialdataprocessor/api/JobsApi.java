/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.1.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.n52.kommonitor.spatialdataprocessor.api;

import org.n52.kommonitor.models.JobOverviewType;
import org.n52.kommonitor.models.JobPOSTInputType;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-26T20:33:13.177747+02:00[Europe/Budapest]")
@Validated
@Tag(name = "jobs", description = "Jobs that are being processed")
@RequestMapping("${openapi.komMonitorSpatialDataProcessor.base-path:}")
public interface JobsApi {

    /**
     * POST /jobs : Submits a processing Job for execution
     * Submits a processing Job for execution
     *
     * @param jobPOSTInputType Definition of Job to be executed (required)
     * @return Successful operation (status code 200)
     *         or Invalid input (status code 405)
     */
    @Operation(
        operationId = "enqueueJob",
        summary = "Submits a processing Job for execution",
        tags = { "jobs" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UUID.class))
            }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
        },
        security = {
            @SecurityRequirement(name = "kommonitor_auth", scopes={ "access:spatial-data-processor" })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/jobs",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    ResponseEntity<UUID> enqueueJob(
        @Parameter(name = "JobPOSTInputType", description = "Definition of Job to be executed", required = true) @Valid @RequestBody JobPOSTInputType jobPOSTInputType
    );


    /**
     * GET /jobs : Returns a list of all currently queued jobs
     * Returns a list of all currently queued jobs
     *
     * @return success (status code 200)
     */
    @Operation(
        operationId = "getAllJobs",
        summary = "Returns a list of all currently queued jobs",
        tags = { "jobs" },
        responses = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = JobOverviewType.class))
            })
        },
        security = {
            @SecurityRequirement(name = "kommonitor_auth", scopes={ "access:spatial-data-processor" })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/jobs",
        produces = { "application/json" }
    )
    ResponseEntity<JobOverviewType> getAllJobs(
        
    );


    /**
     * GET /jobs/{jobId} : Gets the current status of a job
     * Gets the current status of a job
     *
     * @param jobId UUID of the job (required)
     * @return success (status code 200)
     */
    @Operation(
        operationId = "getJob",
        summary = "Gets the current status of a job",
        tags = { "jobs" },
        responses = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = JobOverviewType.class))
            })
        },
        security = {
            @SecurityRequirement(name = "kommonitor_auth", scopes={ "access:spatial-data-processor" })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/jobs/{jobId}",
        produces = { "application/json" }
    )
    ResponseEntity<JobOverviewType> getJob(
        @Parameter(name = "jobId", description = "UUID of the job", required = true) @PathVariable("jobId") UUID jobId
    );

}
