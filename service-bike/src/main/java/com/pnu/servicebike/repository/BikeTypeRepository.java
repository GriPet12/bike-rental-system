package com.pnu.servicebike.repository;

import com.pnu.servicebike.model.BikeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeTypeRepository extends JpaRepository<BikeTypeEntity, Long> {
}
