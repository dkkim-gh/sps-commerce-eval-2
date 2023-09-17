package com.sps.eval.controller;

import com.sps.eval.model.Location;
import com.sps.eval.service.LocationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springdoc.core.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Location> findLocationById(@PathVariable String id) {

        Optional<Location> optionalLocation = locationService.findLocationById(id);
        if(optionalLocation.isPresent()) {
            return new ResponseEntity<>(optionalLocation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Location> saveLocation(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
            @ExampleObject(
                    name = "Organization Example",
                    summary = "Organization Example",
                    externalValue = "http://localhost:8080/openApi/examples/locationPostRequestBodyExample.json"
            )
    })

    ) @org.springframework.web.bind.annotation.RequestBody Location location) {
        locationService.save(location);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    @PageableAsQueryParam
    public ResponseEntity<Page<Location>> findAllLocations(@ParameterObject Pageable pageable) {
        Page<Location> page = locationService.findAll(pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }

}
