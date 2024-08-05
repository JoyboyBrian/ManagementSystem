package com.backend.management.service;

import com.backend.management.model.Amenity;
import com.backend.management.repository.AmenityRepository;
import com.backend.management.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenityService {
    private final AmenityRepository amenityRepository;

    @Autowired
    public AmenityService(AmenityRepository amenityRepository){
        this.amenityRepository = amenityRepository;
    }

    public List<Amenity> list() {
        return amenityRepository.findByAmenityName();
    }
}
