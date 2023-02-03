/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.1.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.n52.kommonitor.spatialdataprocessor.api;

import org.n52.kommonitor.models.JobOverviewType;
import org.n52.kommonitor.models.JobResultType;
import org.n52.kommonitor.models.ProcessType;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-02-03T01:17:34.621019675+01:00[Europe/Berlin]")
@Validated
@Tag(name = "jobs", description = "Jobs that are being processed")
@RequestMapping("${openapi.komMonitorSpatialDataProcessor.base-path:}")
public interface JobsApi {

    /**
     * POST /jobs : Submits a processing Job for execution
     * Submits a processing Job for execution
     *
     * @param processType Definition of Job to be executed (required)
     * @param authorization  (optional)
     * @return Successful operation (status code 200)
     *         or unauthorized (status code 401)
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
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "405", description = "Invalid input")
        },
        security = {
            @SecurityRequirement(name = "kommonitor_auth", scopes={  })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/jobs",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    ResponseEntity<UUID> enqueueJob(
        @Parameter(name = "ProcessType", description = "Definition of Job to be executed", required = true) @Valid @RequestBody ProcessType processType,
        @Parameter(name = "Authorization", description = "") @RequestHeader(value = "Authorization", required = false) String authorization
    );


    /**
     * GET /jobs : Returns a list of all currently queued jobs
     * Returns a list of all currently queued jobs
     *
     * @return success (status code 200)
     *         or unauthorized (status code 401)
     */
    @Operation(
        operationId = "getAllJobs",
        summary = "Returns a list of all currently queued jobs",
        tags = { "jobs" },
        responses = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = JobOverviewType.class))
            }),
            @ApiResponse(responseCode = "401", description = "unauthorized")
        },
        security = {
            @SecurityRequirement(name = "kommonitor_auth", scopes={  })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/jobs",
        produces = { "application/json" }
    )
    ResponseEntity<List<JobOverviewType>> getAllJobs(
        
    );


    /**
     * GET /jobs/{jobId} : Gets the current status of a job
     * Gets the current status of a job
     *
     * @param jobId UUID of the job (required)
     * @return success (status code 200)
     *         or unauthorized (status code 401)
     *         or Job not found (status code 404)
     */
    @Operation(
        operationId = "getJob",
        summary = "Gets the current status of a job",
        tags = { "jobs" },
        responses = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = JobOverviewType.class))
            }),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "404", description = "Job not found")
        },
        security = {
            @SecurityRequirement(name = "kommonitor_auth", scopes={  })
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


    /**
     * GET /jobs/{jobId}/result : Gets the result of a job
     * Gets the result of a job
     *
     * @param jobId UUID of the job (required)
     * @return success (status code 200)
     *         or unauthorized (status code 401)
     *         or Job not found (status code 404)
     */
    @Operation(
        operationId = "getJobResult",
        summary = "Gets the result of a job",
        tags = { "jobs" },
        responses = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = JobResultType.class))
            }),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "404", description = "Job not found")
        },
        security = {
            @SecurityRequirement(name = "kommonitor_auth", scopes={  })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/jobs/{jobId}/result",
        produces = { "application/json" }
    )
    ResponseEntity<JobResultType> getJobResult(
        @Parameter(name = "jobId", description = "UUID of the job", required = true) @PathVariable("jobId") UUID jobId
    );

}
