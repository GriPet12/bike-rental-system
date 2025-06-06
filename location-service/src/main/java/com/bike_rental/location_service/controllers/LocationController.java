package com.bike_rental.location_service.controllers;

import com.bike_rental.location_service.DTO.BikeLocationDto;
import com.bike_rental.location_service.DTO.BikeLocationRequest;
import com.bike_rental.location_service.services.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bikeLocation")
public class LocationController {

    final private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<BikeLocationDto> getBikeLocation(@RequestParam("BikeLocationId") int locationId) {
        BikeLocationDto bikeLocation = locationService.getLocation(locationId);
        return ResponseEntity.status(HttpStatus.OK).body(bikeLocation);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBikeLocation(int locationId){
        locationService.deleteLocation(locationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<Void> pushBikeLocation(@RequestBody BikeLocationRequest location){
        locationService.addBikeLocation(location);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
