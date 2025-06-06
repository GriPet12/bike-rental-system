package com.bike_rental.location_service.DTO;

import java.math.BigDecimal;

public record BikeLocationRequest(
        int bikeId,
        BigDecimal latitude,
        BigDecimal longitude
) {}
