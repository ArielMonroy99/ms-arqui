package com.arqui.vetstore.dto.mapper;

import com.arqui.vetstore.dto.AddressDto;
import com.arqui.vetstore.dto.entity.AddressEntity;

public class AddressMapper {
    public static AddressDto toAddressDto(AddressEntity addressEntity) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressEntity.getId());
        addressDto.setAddress(addressEntity.getAddress());
        addressDto.setLatitude(addressEntity.getLatitude());
        addressDto.setLongitude(addressEntity.getLongitude());
        addressDto.setName(addressEntity.getName());
        return addressDto;
    }

    public static AddressEntity toAddressEntity(AddressDto addressDto) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(addressDto.getId());
        addressEntity.setAddress(addressDto.getAddress());
        addressEntity.setLatitude(addressDto.getLatitude());
        addressEntity.setLongitude(addressDto.getLongitude());
        addressEntity.setName(addressDto.getName());
        addressEntity.setUser(UserMapper.userDtoToEntity(addressDto.getUser()));
        return addressEntity;
    }
}
