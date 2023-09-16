package com.sps.eval.service;

import com.sps.eval.model.Location;
import com.sps.eval.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Page<Location> findAll(Pageable pageable) {
        return locationRepository.findAll(pageable);
    }
}
