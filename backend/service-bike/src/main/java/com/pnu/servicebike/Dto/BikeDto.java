package com.pnu.servicebike.Dto;

import com.pnu.servicebike.model.BikeLocationEntity;
import com.pnu.servicebike.model.BikeTypeEntity;
import com.pnu.servicebike.model.enums.StatusEnum;
import lombok.Data;

import java.sql.Time;

@Data
public class BikeDto {
    private long id;
    private String model;
    private BikeTypeEntity type;
    private StatusEnum status;
    private BikeLocationEntity location;
    private String serialNumber;
    private int batteryLevel;
    private Time createdAt;
    private Time updatedAt;
}
