package com.bike_rental.location_service.services;

import com.bike_rental.location_service.DTO.RouteRequest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaConsumer {

    final private LocationService locationService;
    final private RouteService routeService;

    public KafkaConsumer(LocationService locationService, RouteService routeService) {
        this.locationService = locationService;
        this.routeService = routeService;
    }

    @KafkaListener(topics = "delete-location-topic", groupId = "location")
    public void deleteLocation(int locationId) {
        locationService.deleteLocation(locationId);
    }

    @KafkaListener(topics = "create-route-topic", groupId = "location")
    public void deleteRoute(RouteRequest routeDTO) {
        routeService.createRoute(routeDTO.userId(), routeDTO.bikeId(), routeDTO.firstLocationId());
    }

    @KafkaListener(topics = "delete-route-topic", groupId = "location")
    public void deleteRoute(int routeId) {
        routeService.deleteRoute(routeId);
    }



}
