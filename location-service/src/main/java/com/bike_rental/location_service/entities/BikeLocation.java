package com.bike_rental.location_service.entities;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bike_location")
public class BikeLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(precision = 9, scale = 6,nullable = false)
    public BigDecimal latitude;

    @Column(precision = 9, scale = 6,nullable = false)
    public BigDecimal longitude;

    public BikeLocation(){}

    public BikeLocation(BigDecimal latitude, BigDecimal longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

}