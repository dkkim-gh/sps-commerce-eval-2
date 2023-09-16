package com.sps.eval.controller;


import com.sps.eval.model.Location;
import com.sps.eval.model.Organization;
import com.sps.eval.model.Product;
import com.sps.eval.model.Subscription;
import com.sps.eval.service.OrganizationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable String id) {
        Optional<Organization> optionalOrganization = organizationService.getByPrimaryKey(id);

        if(optionalOrganization.isPresent()) {
            return new ResponseEntity<>(optionalOrganization.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/hardcoded/{id}")
    public ResponseEntity<Organization> getOrganizationById_hardCoded(@PathVariable String id) {

        Organization org = new Organization();
        org.setId(id);
        org.setName("Hard-coded Organization");

        Location loc = new Location();
        loc.setAddress("123 Main Street");
        loc.setCity("Chicago");
        loc.setState("IL");
        loc.setZipCode("12345");

        org.setLocation(loc);


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




        List<Subscription> subscriptions = new ArrayList<>();

        Subscription sub1 = new Subscription();
        sub1.setId("sub1");
        sub1.setOrganization(new Organization());
        sub1.setProducts(products);
        sub1.setDiscount(44);
        sub1.setProducts(products);

        Subscription sub2 = new Subscription();
        sub2.setId("sub2");
        sub2.setOrganization(new Organization());
        sub2.setProducts(products);
        sub2.setDiscount(13);
        sub2.setProducts(products);

        subscriptions.add(sub1);
        subscriptions.add(sub2);

        org.setSubscriptions(subscriptions);

        return new ResponseEntity<>(org, HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<Organization> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
            @ExampleObject(
                    name = "Organization Example",
                    summary = "Organization Example",
                    value = "{\n" +
                            "    \"name\": \"Hard-coded Organization\",\n" +
                            "    \"location\": {\n" +
                            "        \"address\": \"123 Main Street\",\n" +
                            "        \"city\": \"Chicago\",\n" +
                            "        \"state\": \"IL\",\n" +
                            "        \"zipCode\": \"12345\"\n" +
                            "    }\n" +
                            "}"
            )
    })

    ) @org.springframework.web.bind.annotation.RequestBody Organization organization) {



        return new ResponseEntity<>(organization, HttpStatus.OK);
    }



    /*
    @PostMapping
    public ResponseEntity<Organization> create(@RequestBody(description = "Book to add.", required = true,
            content = @Content(
                        schema=@Schema(implementation = Organization.class)
                        )
            ) Organization organization) {



        return new ResponseEntity<>(organization, HttpStatus.CREATED);
    }

     */

}
