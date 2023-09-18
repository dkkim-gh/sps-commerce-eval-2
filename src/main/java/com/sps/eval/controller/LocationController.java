package com.sps.eval.controller;

import com.sps.eval.model.Location;
import com.sps.eval.service.LocationService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springdoc.core.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @RateLimiter(name = "basic")
    @Operation(summary = "Get a Location by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return a Location by ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Location not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content) })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Location> findLocationById(@PathVariable String id,
                                                     @RequestHeader(name = "X-API-KEY", required = false) String apiKey) {

        Optional<Location> optionalLocation = locationService.findLocationById(id);
        if(optionalLocation.isPresent()) {
            return new ResponseEntity<>(optionalLocation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RateLimiter(name = "basic")
    @Operation(summary = "Save an Location. Not including an ID will create a new Location, including an ID will update.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location saved/updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Endpoint not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content) })
    @SecurityRequirements()
    @PostMapping
    public ResponseEntity<Location> saveLocation(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
            @ExampleObject(
                    name = "Organization Example",
                    summary = "Organization Example",
                    value = "{\n" +
                            "    \"address\": \"address1\",\n" +
                            "    \"city\": \"city1\",\n" +
                            "    \"state\": \"state1\",\n" +
                            "    \"zipCode\": \"zipcode1\"\n" +
                            "}"
            )
    })

    ) @org.springframework.web.bind.annotation.RequestBody Location location,
                                                 @RequestHeader(name = "X-API-KEY", required = false) String apiKey) {

        locationService.save(location);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }


    @RateLimiter(name = "basic")
    @Operation(summary = "Get all Locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all Locations",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Endpoint not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content) })
    @GetMapping(value = "/all")
    @PageableAsQueryParam
    public ResponseEntity<Page<Location>> findAllLocations(@ParameterObject Pageable pageable,
                                                           @RequestHeader(name = "X-API-KEY", required = false) String apiKey) {

        Page<Location> page = locationService.findAll(pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }

}
