package com.pnu.servicebike.mapper;

import com.pnu.servicebike.Dto.BikeDto;
import com.pnu.servicebike.model.BikeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BikeMapper {
    BikeDto toDto(BikeEntity bike);
    BikeEntity toEntity(BikeDto dto);
}
