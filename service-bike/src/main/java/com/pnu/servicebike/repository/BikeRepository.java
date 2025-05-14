package com.pnu.servicebike.repository;

import com.pnu.servicebike.model.BikeEntity;
import com.pnu.servicebike.model.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeRepository extends JpaRepository<BikeEntity, Long> {
    List<BikeEntity> findAllByStatus(StatusEnum status);
}
