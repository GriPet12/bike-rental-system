package com.pnu.servicebike.model;

import com.pnu.servicebike.model.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
public class BikeEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private String model;

    @ManyToOne
    private BikeTypeEntity type;

    private StatusEnum status;

    @ManyToOne
    private BikeLocationEntity location;

    private String serialNumber;

    private int batteryLevel;

    private Time createdAt;

    private Time updatedAt;

}
