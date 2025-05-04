package com.pnu.servicebike.service;

import com.pnu.servicebike.Dto.BikeDto;
import com.pnu.servicebike.Dto.BikeLocationDto;
import com.pnu.servicebike.mapper.BikeMapper;
import com.pnu.servicebike.model.BikeEntity;
import com.pnu.servicebike.model.enums.StatusEnum;
import com.pnu.servicebike.repository.BikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BikeService {

    private final BikeRepository bikeRepository;
    private final BikeMapper bikeMapper;

    public BikeDto addBike(BikeDto bikeDto) {
        BikeEntity bike = bikeRepository.save(bikeMapper.toEntity(bikeDto));
        return bikeMapper.toDto(bike);
    }

    public BikeDto updateBike(BikeDto bikeDto) {
        BikeEntity bike = bikeRepository.save(bikeMapper.toEntity(bikeDto));
        return bikeMapper.toDto(bike);
    }

    public void deleteBike(long id) {
        bikeRepository.deleteById(id);
    }

    public BikeDto getBikeById(long id) {
        return bikeRepository.findById(id)
                .map(bikeMapper::toDto)
                .orElse(null);
    }

    public List<BikeDto> listAvailableBikes() {
        List<BikeEntity> bikes = bikeRepository.findAllByStatus(StatusEnum.AVAILABLE);
        return bikes.stream()
                .map(bikeMapper::toDto)
                .collect(Collectors.toList());
    }

    public boolean changeBikeStatus(long id, StatusEnum status) {
        BikeEntity bike = bikeRepository.findById(id).orElse(null);
        if (bike != null) {
            bike.setStatus(status);
            bikeRepository.save(bike);
            return true;
        }
        return false;
    }

    public void markAsMaintenance(long id) {
        BikeEntity bike = bikeRepository.findById(id).orElse(null);
        if (bike != null) {
            bike.setStatus(StatusEnum.MAINTENANCE);
            bikeRepository.save(bike);
        }
    }

    public void reportBikeIssue(long id) {
        BikeEntity bike = bikeRepository.findById(id).orElse(null);
        BikeDto bikeDto = bikeMapper.toDto(bike);
        //todo send notification to admin with notification service
    }

    public List<BikeDto> getBikesByLocation(BikeLocationDto locationDto) {
        List<BikeEntity> allBikes = bikeRepository.findAll();

        return allBikes.stream()
                .filter(bike -> bike.getLocation() != null)
                .sorted((bike1, bike2) -> {
                    double distance1 = calculateDistance(
                            locationDto.getLatitude(), locationDto.getLongitude(),
                            bike1.getLocation().getLatitude(), bike1.getLocation().getLongitude());

                    double distance2 = calculateDistance(
                            locationDto.getLatitude(), locationDto.getLongitude(),
                            bike2.getLocation().getLatitude(), bike2.getLocation().getLongitude());

                    return Double.compare(distance1, distance2);
                })
                .limit(20)
                .map(bikeMapper::toDto)
                .collect(Collectors.toList());
    }

    private double calculateDistance(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
        final int R = 6371;

        double latDistance = Math.toRadians(lat2.subtract(lat1).doubleValue());
        double lonDistance = Math.toRadians(lon2.subtract(lon1).doubleValue());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1.doubleValue())) * Math.cos(Math.toRadians(lat2.doubleValue()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
