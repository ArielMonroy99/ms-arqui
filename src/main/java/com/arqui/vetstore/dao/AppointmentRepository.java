package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.AppointmentEntity;
import com.arqui.vetstore.dto.VeterinaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
    List<AppointmentEntity> getAppointmentEntitiesByVeterinaryAndDateGreaterThanEqualAndDateLessThanEqualAndStatus(VeterinaryEntity veterinary, Date startDate, Date endDate,Integer status);
    Page<AppointmentEntity> getAppointmentEntitiesByUserId(Integer userId,  PageRequest of);
}
