package com.sps.eval.controller;


import com.sps.eval.model.Organization;
import com.sps.eval.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @Operation(summary = "Get an Organization by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Organization",
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


    @Operation(summary = "Save an Organization. Not including an ID will create a new Organization, including an ID will update.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Organization saved/updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Organization.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Endpoint not found",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<Organization> saveOrganization(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
            @ExampleObject(
                    name = "Organization Example 1",
                    summary = "Organization with no Subscriptions",
                    value = "{\n" +
                            "  \"name\": \"Org 1\",\n" +
                            "  \"location\": {\n" +
                            "    \"address\": \"address1\",\n" +
                            "    \"city\": \"city1\",\n" +
                            "    \"state\": \"state1\",\n" +
                            "    \"zipCode\": \"zipcode1\"\n" +
                            "  }\n" +
                            "}"
            ),
            @ExampleObject(
                    name = "Organization Example 2",
                    summary = "Organization with a Subscription (Product names must be unique)",
                    value = "{\n" +
                            "    \"name\": \"Org 1\",\n" +
                            "    \"location\": {\n" +
                            "        \"address\": \"address1\",\n" +
                            "        \"city\": \"city1\",\n" +
                            "        \"state\": \"state1\",\n" +
                            "        \"zipCode\": \"zipcode1\"\n" +
                            "    },\n" +
                            "    \"subscriptions\": [\n" +
                            "        {\n" +
                            "            \"products\": [\n" +
                            "                {\n" +
                            "                    \"name\": \"product 11\",\n" +
                            "                    \"description\": \"product 1\",\n" +
                            "                    \"subproducts\": [],\n" +
                            "                    \"price\": 10.0\n" +
                            "                },\n" +
                            "                {\n" +
                            "                    \"name\": \"product 22\",\n" +
                            "                    \"description\": \"product 2\",\n" +
                            "                    \"subproducts\": [],\n" +
                            "                    \"price\": 15.0\n" +
                            "                },\n" +
                            "                {\n" +
                            "                    \"name\": \"product 33\",\n" +
                            "                    \"description\": \"product 3\",\n" +
                            "                    \"subproducts\": [],\n" +
                            "                    \"price\": 19.99\n" +
                            "                },\n" +
                            "                {\n" +
                            "                    \"name\": \"product 44\",\n" +
                            "                    \"description\": \"product 4\",\n" +
                            "                    \"subproducts\": [],\n" +
                            "                    \"price\": 34.99\n" +
                            "                }\n" +
                            "            ],\n" +
                            "            \"discount\": 10.0\n" +
                            "        }\n" +
                            "    ]\n" +
                            "}"
            )
    })

    ) @org.springframework.web.bind.annotation.RequestBody Organization organization) {

        organizationService.save(organization);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }



    @Operation(summary = "Get all Organizations (example of pagination and sorting)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns all Organizations",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Organization.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Endpoint not found",
                    content = @Content) })
    @GetMapping(value = "/all")
    @PageableAsQueryParam
    public ResponseEntity<Page<Organization>> findAllOrganizations(@ParameterObject Pageable pageable) {
        Page<Organization> page = organizationService.findAll(pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }



}
