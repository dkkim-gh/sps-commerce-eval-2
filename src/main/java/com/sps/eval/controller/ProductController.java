package com.sps.eval.controller;

import com.sps.eval.model.Product;
import com.sps.eval.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Product> save(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /*
    @GetMapping("/hardcoded/{id}")
    public ResponseEntity<Product> getProductById_hardCoded(@PathVariable String id) {

        Product prod = new Product();
        prod.setId(id);
        prod.setName("Product Name");
        prod.setDescription("Product Description");

        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

     */

}
