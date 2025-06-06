package com.bike_rental.location_service.entities;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "bike_id")
    public int bikeId;

    @Column(name = "user_id")
    public int userId;

    @Column(name = "head_location_id")
    public int headLocationId;

    @OneToOne
    @JoinColumn(name="head_location_id", updatable = false, insertable = false)
    public BikeLocation headLocation;

    @Column
    public int distance;

    @OneToMany()
    public List<BikeLocation> points = new ArrayList<>();

    public Route(){}

    public Route(int bikeId, int userId, int headLocationId) {
        this.bikeId = bikeId;
        this.userId = userId;
        this.distance = 0;
        this.headLocationId = headLocationId;
    }
}
