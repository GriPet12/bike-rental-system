package com.pnu.servicebike.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BikeLocationDto {
    private long id;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
