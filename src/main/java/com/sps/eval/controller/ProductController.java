package com.sps.eval.controller;

import com.sps.eval.model.Organization;
import com.sps.eval.model.Product;
import com.sps.eval.service.ProductService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {

        Optional<Product> optionalProduct = productService.findProductById(id);

        if(optionalProduct.isPresent()) {
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    @PageableAsQueryParam
    public ResponseEntity<Page<Organization>> findAllLocations(Pageable pageable) {
        Page<Product> page = productService.findAll(pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }


}
