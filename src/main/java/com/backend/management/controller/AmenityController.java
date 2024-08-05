package com.backend.management.controller;

import com.backend.management.exception.ReservationNotFoundException;
import com.backend.management.model.Amenity;
import com.backend.management.model.User;
import com.backend.management.service.AmenityService;
import com.backend.management.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AmenityController {

    private final AmenityService amenityService;

    @Autowired
    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }


    @GetMapping("/amenity")
    public List<Amenity> getAmenityList(

    ) {
        return amenityService.list();
    }
}
