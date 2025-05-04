package com.pnu.servicebike.repository;

import com.pnu.servicebike.model.BikeLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeLocationRepository extends JpaRepository<BikeLocationEntity, Long> {
}
