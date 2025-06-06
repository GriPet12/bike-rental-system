package com.bike_rental.location_service.services;

import com.bike_rental.location_service.DTO.BikeLocationDto;
import com.bike_rental.location_service.DTO.RouteDTO;
import com.bike_rental.location_service.entities.BikeLocation;
import com.bike_rental.location_service.entities.Route;
import com.bike_rental.location_service.repositories.BikeLocationRepository;
import com.bike_rental.location_service.repositories.RouteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    final private RouteRepository routeRepository;
    final private BikeLocationRepository bikeLocationRepository;
    final private LocationService locationService;

    @Autowired
    public RouteService(RouteRepository routeRepository, BikeLocationRepository bikeLocationRepository,
                        @Lazy LocationService locationService) {
        this.routeRepository = routeRepository;
        this.bikeLocationRepository = bikeLocationRepository;
        this.locationService = locationService;
    }

    public double calculateDistance(BikeLocation location1, BikeLocation location2){
        final int R = 6371;
        BigDecimal longitude1 = location1.longitude;
        BigDecimal latitude1  = location1.latitude;
        BigDecimal longitude2 = location2.longitude;
        BigDecimal latitude2  = location2.latitude;


        double latDistance = Math.toRadians(latitude2.subtract(latitude1).doubleValue());
        double lonDistance = Math.toRadians(longitude2.subtract(longitude1).doubleValue());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude1.doubleValue())) * Math.cos(Math.toRadians(latitude2.doubleValue()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public void updateRoute(int bikeId, BikeLocation location){
        Route route = routeRepository.findByBikeId(bikeId);
        if(route != null) {
            route.points.add(location);
            int d = (int) calculateDistance(route.headLocation, location);
            route.distance += d;

            route.headLocationId = location.id;

            routeRepository.save(route);
        }

    }

    @Transactional
    public int createRoute(int userId, int bikeId, int firstPointId){
        BikeLocation firstPoint = bikeLocationRepository.findById(firstPointId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong bikeLocation id")
        );

        Route r = routeRepository.findByBikeId(bikeId);
        if(r != null){
            routeRepository.delete(r);
            routeRepository.flush();
        }

        Route route = new Route(userId, bikeId, firstPointId);
        route.points.add(firstPoint);
        route = routeRepository.save(route);

        return route.id;
    }

    public void deleteRoute(int routeId){
        if(routeRepository.existsById(routeId)){
            routeRepository.deleteById(routeId);
            routeRepository.flush();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong route id");
        }
    }

    public RouteDTO getRoute(int routeId){
        Route route = routeRepository.findById(routeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong route id")
        );

        List<BikeLocationDto> points = new ArrayList<>();
        RouteDTO routeDTO = new RouteDTO(route.id, route.userId, route.bikeId, route.distance, points);
        for(BikeLocation location : route.points){
            points.add(locationService.getDTO(location));
        }

        return routeDTO;
    }

}
