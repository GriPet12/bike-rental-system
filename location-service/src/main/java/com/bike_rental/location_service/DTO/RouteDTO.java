package com.bike_rental.location_service.DTO;


import java.util.List;

public record RouteDTO(
        int id,
        int userId,
        int bikeId,
        int distance,
        List<BikeLocationDto> points
) {}
