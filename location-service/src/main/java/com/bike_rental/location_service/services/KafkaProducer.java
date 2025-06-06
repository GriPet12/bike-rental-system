package com.bike_rental.location_service.services;

import com.bike_rental.location_service.DTO.BikeLocationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, BikeLocationEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, BikeLocationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void pushBikeLocation(BikeLocationEvent event){
        kafkaTemplate.send("bike-location-topic", event);
    }

}