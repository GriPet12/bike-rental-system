package com.bike_rental.location_service.controllers;

import com.bike_rental.location_service.DTO.RouteDTO;
import com.bike_rental.location_service.DTO.RouteRequest;
import com.bike_rental.location_service.services.RouteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/route")
public class RouteController {

    final private RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("{id}")
    public ResponseEntity<RouteDTO> getRoute(@PathVariable("id")int routeId){
        RouteDTO route = routeService.getRoute(routeId);
        return ResponseEntity.status(HttpStatus.OK).body(route);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable("id")int routeId){
       routeService.deleteRoute(routeId);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<Integer> startRoute(@RequestBody RouteRequest routeRequest){
        int id = routeService.createRoute(routeRequest.userId(), routeRequest.bikeId(), routeRequest.firstLocationId());
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

}
