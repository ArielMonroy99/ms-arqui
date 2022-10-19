package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.entity.AppointmentEntity;
import com.arqui.vetstore.dto.entity.VeterinaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
    List<AppointmentEntity> getAppointmentEntitiesByVeterinaryAndDateGreaterThanEqualAndDateLessThanEqual(VeterinaryEntity veterinary, Date startDate, Date endDate);
    List<AppointmentEntity> getAppointmentEntitiesByUserIdAndStatus(Integer userId,Integer status);
}
