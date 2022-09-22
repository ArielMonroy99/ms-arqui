package com.arqui.vetstore.dto.mapper;

import com.arqui.vetstore.dto.VeterinaryDto;
import com.arqui.vetstore.dto.entity.VeterinaryEntity;

public class VeterinaryMapper {
    public static VeterinaryDto veterinaryToDto(VeterinaryEntity veterinaryEntity){
        return new VeterinaryDto(
                veterinaryEntity.getId(),
                veterinaryEntity.getName(),
                veterinaryEntity.getLastname(),
                veterinaryEntity.getAddress(),
                veterinaryEntity.getPhone(),
                veterinaryEntity.getIdNumber(),
                veterinaryEntity.getSchedule()
        );
    }
    public static VeterinaryEntity veterinaryToEntity(VeterinaryDto veterinaryDto){
        return new VeterinaryEntity(
                veterinaryDto.getId(),
                veterinaryDto.getName(),
                veterinaryDto.getLastname(),
                veterinaryDto.getAddress(),
                veterinaryDto.getPhone(),
                veterinaryDto.getIdNumber()
        );
    }
}
