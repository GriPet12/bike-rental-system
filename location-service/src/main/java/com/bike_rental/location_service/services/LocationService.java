package com.bike_rental.location_service.services;

import com.bike_rental.location_service.DTO.BikeLocationDto;
import com.bike_rental.location_service.DTO.BikeLocationRequest;
import com.bike_rental.location_service.entities.BikeLocation;
import com.bike_rental.location_service.repositories.BikeLocationRepository;
import com.bike_rental.location_service.repositories.RouteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LocationService {

    final private BikeLocationRepository bikeLocationRepository;
    final private RouteRepository routeRepository;
    final private RouteService routeService;

    public LocationService(BikeLocationRepository bikeLocationRepository, RouteRepository routeRepository, RouteService routeService) {
        this.bikeLocationRepository = bikeLocationRepository;
        this.routeRepository = routeRepository;
        this.routeService = routeService;
    }

    public BikeLocationDto getDTO(BikeLocation location){
        return new BikeLocationDto(location.id, location.latitude, location.longitude);
    }

    public BikeLocationDto getLocation(int id) {
        BikeLocation location = bikeLocationRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong bikeLocation id")
        );
        return getDTO(location);
    }

    public void deleteLocation(int locationId) {
        if(!bikeLocationRepository.existsById(locationId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong bikeLocation id");
        }
        else{
            bikeLocationRepository.deleteById(locationId);
        }
    }

    public void addBikeLocation(BikeLocationRequest locationRequest) {
        BikeLocation location = new BikeLocation(locationRequest.latitude(), locationRequest.longitude());
        location = bikeLocationRepository.save(location);

        int bikeId = locationRequest.bikeId();
        if(routeRepository.existsByBikeId(bikeId)){
            routeService.updateRoute(bikeId, location);
        }

    }

}
