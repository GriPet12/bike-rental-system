package com.bike_rental.location_service.DTO;

import java.math.BigDecimal;

public record BikeLocationDto(
    long id,
    BigDecimal latitude,
    BigDecimal longitude
) {};
