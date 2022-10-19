package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.AppointmentBl;
import com.arqui.vetstore.dto.AppointmentDto;
import com.arqui.vetstore.dto.entity.CancelationEntity;
import com.arqui.vetstore.dto.entity.ScheduleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/appointment")
public class AppointmentApi {

    private final AppointmentBl appointmentBl;
    public static final Logger logger = LoggerFactory.getLogger(AppointmentApi.class);
    @Autowired
    public AppointmentApi(AppointmentBl appointmentBl) {
        this.appointmentBl = appointmentBl;
    }

    @GetMapping
    public List<ScheduleEntity> getAvaliableSchedule(@RequestParam Integer vetId, @RequestParam Date startDate,  @RequestParam Date endDate){
        logger.info("vetId {}, startDate {} , endDate {}",vetId,startDate,endDate);
        return  appointmentBl.getAvailableSchedules(vetId,  startDate,  endDate);
    }

    @PostMapping
    public AppointmentDto saveAppointment(@RequestBody AppointmentDto appointmentDto){
        logger.info("appointmentDto {}",appointmentDto);
        return appointmentBl.saveAppointmet(appointmentDto);
    }
    @GetMapping("/user/{userId}")
    public List<AppointmentDto> getAppointmentsByUser(@PathVariable Integer userId){
        logger.info("userId {}",userId);
        return appointmentBl.getAppointmentsByUser(userId);
    }

    @PostMapping("/cancel")
    public String cancelAppointment(@RequestBody CancelationEntity cancelationEntity){
        logger.info("appointmentDto {}",cancelationEntity);
        return appointmentBl.cancelAppointment(cancelationEntity);
    }
}
