package com.sps.eval.controller;

import com.sps.eval.model.Location;
import com.sps.eval.model.Organization;
import com.sps.eval.model.Product;
import com.sps.eval.model.Subscription;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getById(@PathVariable String id) {

        Subscription sub = new Subscription();
        sub.setId(id);

        sub.setDiscount(12);

        Product prod1 = new Product();
        prod1.setId("prod1");
        prod1.setName("Product Name 1");
        prod1.setDescription("Product Description 1");
        prod1.setPrice(50);

        Product prod2 = new Product();
        prod2.setId("prod2");
        prod2.setName("Product Name 2");
        prod2.setDescription("Product Description 2");
        prod2.setPrice(200);

        List<Product> products = new ArrayList<>();
        products.add(prod1);
        products.add(prod2);
        sub.setProducts(products);

        Organization org = new Organization();
        org.setId(id);
        org.setName("Hard-coded Organization");

        Location loc = new Location();
        loc.setAddress("123 Main Street");
        loc.setCity("Chicago");
        loc.setState("IL");
        loc.setZipCode("12345");
        org.setLocation(loc);
        sub.setOrganization(org);

        return new ResponseEntity<>(sub, HttpStatus.OK);
    }
}
