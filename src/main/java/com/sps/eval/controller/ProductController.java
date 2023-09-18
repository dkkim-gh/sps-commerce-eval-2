package com.sps.eval.controller;

import com.sps.eval.model.Organization;
import com.sps.eval.model.Product;
import com.sps.eval.service.ProductService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RateLimiter(name = "basic")
    @Operation(summary = "Get an Product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Organization.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Organization not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id,
                                                  @RequestHeader(name = "X-API-KEY", required = false) String apiKey) {

        Optional<Product> optionalProduct = productService.findProductById(id);

        if(optionalProduct.isPresent()) {
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RateLimiter(name = "basic")
    @Operation(summary = "Save an Product. Not including an ID will create a new Product, including an ID will update.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Organization saved/updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Endpoint not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<Product> saveProduct(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
            @ExampleObject(
                    name = "Product Example 1",
                    summary = "Product Example",
                    value = "{\n" +
                            "    \"name\": \"Product from Swagger Name\",\n" +
                            "    \"description\": \"Product from Swagger Description\",\n" +
                            "    \"subproducts\": [],\n" +
                            "    \"price\": 10.0\n" +
                            "}"
            ),
            @ExampleObject(
                    name = "Product Example 2",
                    summary = "Product Example with a Subproduct",
                    value = "{\n" +
                            "    \"name\": \"product 1\",\n" +
                            "    \"description\": \"product 1\",\n" +
                            "    \"subproducts\": [\n" +
                            "        {\n" +
                            "            \"name\": \"subproduct 1\",\n" +
                            "            \"description\": \"subproduct 1\",\n" +
                            "            \"subproducts\": [],\n" +
                            "            \"price\": 25.0\n" +
                            "        }\n" +
                            "    ],\n" +
                            "    \"price\": 10.0\n" +
                            "}"
            ),
    })

    ) @org.springframework.web.bind.annotation.RequestBody Product product,
                                               @RequestHeader(name = "X-API-KEY", required = false) String apiKey) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RateLimiter(name = "basic")
    @Operation(summary = "Get all Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all Products",
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
    public ResponseEntity<Page<Product>> findAllProducts(@ParameterObject Pageable pageable,
                                                         @RequestHeader(name = "X-API-KEY", required = false) String apiKey) {
        Page<Product> page = productService.findAll(pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }


}
