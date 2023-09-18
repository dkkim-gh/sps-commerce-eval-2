package com.sps.eval.controller;

import com.sps.eval.message.ResponseMessage;
import com.sps.eval.model.Organization;
import com.sps.eval.model.Product;
import com.sps.eval.model.Subscription;
import com.sps.eval.service.SubscriptionService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @RateLimiter(name = "basic")
    @Operation(summary = "Get a Subscription by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Subscription",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Subscription.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Subscription not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable String id,
                                                            @RequestHeader(name = "X-API-KEY", required = false) String apiKey) {

        Optional<Subscription> subscriptionOptional = subscriptionService.findSubscriptionById(id);

        if(subscriptionOptional.isPresent()) {
            return new ResponseEntity<>(subscriptionOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RateLimiter(name = "basic")
    @Operation(summary = "Save an Subscription. Not including an ID will create a new Subscription, including an ID will update.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Organization saved/updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Subscription.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Endpoint not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content) })
    @SecurityRequirements()
    @PostMapping
    public ResponseEntity<Subscription> saveSubscription(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
            @ExampleObject(
                    name = "Subscription Example 1 (TODO)",
                    summary = "Subscription Example (TODO)",
                    value = "{\n" +
                            "  \"name\": \"Org 1\",\n" +
                            "  \"location\": {\n" +
                            "    \"address\": \"address1\",\n" +
                            "    \"city\": \"city1\",\n" +
                            "    \"state\": \"state1\",\n" +
                            "    \"zipCode\": \"zipcode1\"\n" +
                            "  }\n" +
                            "}"
            )
    })

    ) @org.springframework.web.bind.annotation.RequestBody Subscription subscription,
                                                         @RequestHeader(name = "X-API-KEY", required = false) String apiKey) {
        subscriptionService.save(subscription);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }



    @RateLimiter(name = "basic")
    @Operation(summary = "Get all Subscriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all Subscriptions",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Endpoint not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content) })
    @GetMapping(value = "/all")
    @PageableAsQueryParam
    public ResponseEntity<Page<Subscription>> findAllSubscriptions(@ParameterObject Pageable pageable,
                                                                   @RequestHeader(name = "X-API-KEY", required = false) String apiKey) {
        Page<Subscription> page = subscriptionService.findAll(pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }


    @RateLimiter(name = "basic")
    @Operation(summary = "Upload a file to import Subscriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully processed file to import Subscriptions",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Endpoint not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content) })
    @PostMapping(value = "/upload")
    public ResponseEntity<ResponseMessage> uploadSubscriptionCsvFile(@RequestParam("file") MultipartFile file,
                                                                     @RequestHeader(name = "X-API-KEY", required = false) String apiKey) {

        List<Subscription> uploadedSubscriptions = subscriptionService.importSubscriptionCsvFile(file);
        ResponseMessage responseMessage = new ResponseMessage("Processed " + uploadedSubscriptions.size() + " subscriptions.");
        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

}
