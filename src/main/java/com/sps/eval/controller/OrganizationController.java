package com.sps.eval.controller;


import com.sps.eval.model.Organization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {


    @GetMapping("/{id}")
    public ResponseEntity<Organization> getById(@PathVariable String id) {

        Organization org = new Organization();
        org.setId(id);
        org.setName("Hard-coded Organization");

        return new ResponseEntity<>(org, HttpStatus.OK);
    }

}
