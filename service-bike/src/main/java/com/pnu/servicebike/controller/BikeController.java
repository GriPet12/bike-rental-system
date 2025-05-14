package com.pnu.servicebike.controller;

import com.pnu.servicebike.Dto.BikeDto;
import com.pnu.servicebike.Dto.BikeLocationDto;
import com.pnu.servicebike.model.enums.StatusEnum;
import com.pnu.servicebike.service.BikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bikes")
public class BikeController {

    private final BikeService bikeService;

    @PostMapping
    public BikeDto addBike(BikeDto bikeDto) {
        return bikeService.addBike(bikeDto);
    }

    @PutMapping
    public BikeDto updateBike(BikeDto bikeDto) {
        return bikeService.updateBike(bikeDto);
    }

    @DeleteMapping
    public void deleteBike(long id) {
        bikeService.deleteBike(id);
    }

    @GetMapping("/{id}")
    public BikeDto getBikeById(@PathVariable long id) {
        return bikeService.getBikeById(id);
    }

    @GetMapping("/available")
    public List<BikeDto> listAvailableBikes() {
        return bikeService.listAvailableBikes();
    }

    @PutMapping("/status/{id}")
    public boolean changeBikeStatus(@PathVariable long id, @RequestParam String status) {
        StatusEnum statusEnum = StatusEnum.valueOf(status.toUpperCase());
        return bikeService.changeBikeStatus(id, statusEnum);
    }

    @PutMapping("/maintenance/{id}")
    public void markAsMaintenance(@PathVariable long id) {
        bikeService.markAsMaintenance(id);
    }

    @GetMapping("/location")
    public List<BikeDto> getBikesByLocation(BikeLocationDto bikeLocationDto) {
        return bikeService.getBikesByLocation(bikeLocationDto);
    }

}
