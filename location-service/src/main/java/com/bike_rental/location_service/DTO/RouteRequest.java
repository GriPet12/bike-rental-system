package com.bike_rental.location_service.DTO;

public record RouteRequest(
        int userId,
        int bikeId,
        int firstLocationId
) {}
