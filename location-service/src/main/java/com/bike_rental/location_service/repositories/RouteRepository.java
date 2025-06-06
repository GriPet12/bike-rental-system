package com.bike_rental.location_service.repositories;

import com.bike_rental.location_service.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    public Route findByBikeId(int bikeId);

    boolean existsByBikeId(int bikeId);

    void deleteByBikeId(int bikeId);

}
