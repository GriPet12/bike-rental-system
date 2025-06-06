package com.bike_rental.location_service.DTO;

import java.math.BigDecimal;

public record BikeLocationEvent(
        int id,
        int bikeId,
        BigDecimal latitude,
        BigDecimal longitude
) {}
