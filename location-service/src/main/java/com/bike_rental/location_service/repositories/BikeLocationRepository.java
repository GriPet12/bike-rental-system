package com.bike_rental.location_service.repositories;

import com.bike_rental.location_service.entities.BikeLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeLocationRepository extends JpaRepository<BikeLocation, Integer> {

    public BikeLocation getById(int id);

    public boolean existsById(int id);

    public void deleteById(int id);

    public BikeLocation save(BikeLocation bikeLocation);

}
