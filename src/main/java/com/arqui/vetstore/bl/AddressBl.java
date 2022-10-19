package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.AddressRepository;
import com.arqui.vetstore.dto.AddressDto;
import com.arqui.vetstore.dto.entity.AddressEntity;
import com.arqui.vetstore.dto.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AddressBl {
    private AddressRepository addressRepository;
    public static final Logger logger = LoggerFactory.getLogger(AddressBl.class);
    @Autowired
    public AddressBl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDto saveAddress(AddressDto address){
        logger.info("Saving address {}", address);
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddress(address.getAddress());
        addressEntity.setLatitude(address.getLatitude());
        addressEntity.setLongitude(address.getLongitude());
        addressEntity.setName(address.getName());
        addressEntity.setUser(new UserEntity(address.getUser().getId()));
        addressEntity.setStatus(1);
        addressEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        logger.info("Address saved {}", addressEntity);
        addressEntity = addressRepository.save(addressEntity);
        address.setId(addressEntity.getId());

        return address;
    }

    public AddressDto updateAddress(AddressDto address){
        logger.info("Updating address {}", address);
        AddressEntity addressEntity = addressRepository.findById(address.getId()).orElseThrow(
                () -> new RuntimeException("Address not found")
        );
        addressEntity.setAddress(address.getAddress());
        addressEntity.setLatitude(address.getLatitude());
        addressEntity.setLongitude(address.getLongitude());
        addressEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return address;
    }
    public void deleteAddress(Integer id){
        AddressEntity addressEntity = addressRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Address not found")
        );
        addressEntity.setStatus(0);
        addressEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        addressRepository.save(addressEntity);
    }

    public Page<AddressDto> getAllAddresses(Integer page, Integer size, Integer userId){
        Page<AddressEntity> addresses = addressRepository.findAllByUserIdAndStatus( PageRequest.of(page, size) , userId,1);
        return addresses.map(addressEntity -> {
            AddressDto addressDto = new AddressDto();
            addressDto.setId(addressEntity.getId());
            addressDto.setAddress(addressEntity.getAddress());
            addressDto.setLatitude(addressEntity.getLatitude());
            addressDto.setLongitude(addressEntity.getLongitude());
            addressDto.setName(addressEntity.getName());
            addressDto.setUser(addressEntity.getUser().toDto());

            return addressDto;
        });
    }



}
