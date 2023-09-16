package com.sps.eval.controller;

import com.sps.eval.model.Location;
import com.sps.eval.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

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
    public ResponseEntity<Location> save(@RequestBody Location location) {
        locationService.save(location);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

}
