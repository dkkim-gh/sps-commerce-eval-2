package com.sps.eval.controller;


import com.sps.eval.model.Location;
import com.sps.eval.model.Organization;
import com.sps.eval.model.Product;
import com.sps.eval.model.Subscription;
import com.sps.eval.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.aspectj.weaver.ast.Or;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @Operation(summary = "Get an Organization by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Organization.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Organization not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable String id) {
        Optional<Organization> optionalOrganization = organizationService.findOrganizationById(id);

        if(optionalOrganization.isPresent()) {
            return new ResponseEntity<>(optionalOrganization.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<Organization> saveOrganization(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
            @ExampleObject(
                    name = "Organization Example",
                    summary = "Organization Example",
                    externalValue = "http://localhost:8080/openApi/examples/organizationPostRequestBodyExample.json"
            )
    })

    ) @org.springframework.web.bind.annotation.RequestBody Organization organization) {

        organizationService.save(organization);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }



    @GetMapping(value = "/all")
    @PageableAsQueryParam
    public ResponseEntity<Page<Organization>> findAllLocations(Pageable pageable) {
        Page<Organization> page = organizationService.findAll(pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }



}
