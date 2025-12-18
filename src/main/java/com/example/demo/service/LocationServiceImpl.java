package com.example.demo.service.impl;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.stereotype.Service;
import com.example.demo.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repo;

    public LocationServiceImpl(LocationRepository repo) {
        this.repo = repo;
    }

    @Override
    public Location add(Location l) {
        if (l.getLatitude() > 90 || l.getLatitude() < -90)
            throw new IllegalArgumentException("Invalid latitude");

        return repo.save(l);
    }
}
