package com.sps.eval.controller;

import com.sps.eval.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping("{id}")
    public ResponseEntity<Product> getById(@PathVariable String id) {

        Product prod = new Product();
        prod.setId(id);
        prod.setName("Product Name");
        prod.setDescription("Product Description");

        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

}
