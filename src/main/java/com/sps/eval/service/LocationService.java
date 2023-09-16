package com.sps.eval.service;

import com.sps.eval.model.Location;
import com.sps.eval.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Optional<Location> findLocationById(String id) {
        return locationRepository.findById(id);
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }
}
