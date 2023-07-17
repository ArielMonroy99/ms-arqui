package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.ScheduleRepostory;
import com.arqui.vetstore.dao.VeterinaryRepository;
import com.arqui.vetstore.dto.VeterinaryDto;
import com.arqui.vetstore.dto.VeterinaryEntity;
import com.arqui.vetstore.dto.VeterinaryMapper;
import com.arqui.vetstore.configure.error.VeterinaryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@Service
public class VeterinaryBl {
    private VeterinaryRepository veterinaryRepository;
    private ScheduleRepostory scheduleRepostory;
    public static final Logger logger = LoggerFactory.getLogger(VeterinaryBl.class);

    @Autowired
    public VeterinaryBl(VeterinaryRepository veterinaryRepository, ScheduleRepostory scheduleRepostory) {
        this.veterinaryRepository = veterinaryRepository;
        this.scheduleRepostory = scheduleRepostory;
    }

    public VeterinaryDto saveVeterinary(VeterinaryDto newVeterinary){
        logger.info("Saving new veterinary {}", newVeterinary);
        logger.info("Saaving shedules {}", newVeterinary.getSchedules());
        VeterinaryEntity veterinaryEntity = VeterinaryMapper.veterinaryToEntity(newVeterinary);
        veterinaryEntity.setStatus(1);
        veterinaryEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        veterinaryEntity.setSchedule(newVeterinary.getSchedules().stream().map(
                schedule -> {
                    schedule.setVeterinary(veterinaryEntity);
                    return schedule;
                }).collect(Collectors.toList()));
        VeterinaryEntity vet = veterinaryRepository.save(veterinaryEntity);
        newVeterinary.setId(vet.getId());
        return newVeterinary;
    }

    public VeterinaryDto getVeterinaryById(Integer id){
        logger.info("Getting veterinary by id {}", id);
        VeterinaryEntity veterinaryEntity = veterinaryRepository.findById(id)
                .orElseThrow(() -> new VeterinaryNotFoundException("Veterinary not found"));
        return VeterinaryMapper.veterinaryToDto(veterinaryEntity);
    }

    public Page<VeterinaryDto> getAllVeterinaries( Integer page, Integer size){
        logger.info("Getting all veterinaries");
        Pageable pageable = PageRequest.of(page, size);
        Page<VeterinaryEntity> veterinaryEntities = veterinaryRepository.findAllByStatus(1,pageable);
        return veterinaryEntities.map(VeterinaryMapper::veterinaryToDto);
    }

    public VeterinaryDto updateVeterinary(VeterinaryDto veterinaryDto){
        logger.info("Updating veterinary {}", veterinaryDto);
        VeterinaryEntity veterinaryEntity = veterinaryRepository.findById(veterinaryDto.getId())
                .orElseThrow(() -> new VeterinaryNotFoundException("Veterinary not found"));
        veterinaryEntity.setName(veterinaryDto.getName());
        veterinaryEntity.setLastname(veterinaryDto.getLastname());
        veterinaryEntity.setAddress(veterinaryDto.getAddress());
        veterinaryEntity.setPhone(veterinaryDto.getPhone());
        veterinaryEntity.setIdNumber(veterinaryDto.getIdNumber());
        veterinaryEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        veterinaryRepository.save(veterinaryEntity);
        return VeterinaryMapper.veterinaryToDto(veterinaryEntity);
    }


}
