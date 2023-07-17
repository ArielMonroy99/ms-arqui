package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.AppointmentRepository;
import com.arqui.vetstore.dao.CancelationRepository;
import com.arqui.vetstore.dao.UserRepository;
import com.arqui.vetstore.dao.VeterinaryRepository;
import com.arqui.vetstore.dto.AppointmentDto;
import com.arqui.vetstore.dto.VeterinaryDto;
import com.arqui.vetstore.dto.AppointmentEntity;
import com.arqui.vetstore.dto.CancelationEntity;
import com.arqui.vetstore.dto.ScheduleEntity;
import com.arqui.vetstore.dto.VeterinaryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AppointmentBl {

    private final AppointmentRepository appointmentRepository;
    private final VeterinaryRepository veterinaryRepository;
    private final UserRepository userRepository;
    private final CancelationRepository cancelationRepository;

    public static final Logger logger = LoggerFactory.getLogger(AppointmentBl.class);
    @Autowired
    public AppointmentBl(AppointmentRepository appointmentRepository, VeterinaryRepository veterinaryRepository, UserRepository userRepository, CancelationRepository cancelationRepository) {
        this.appointmentRepository = appointmentRepository;
        this.veterinaryRepository = veterinaryRepository;
        this.userRepository = userRepository;
        this.cancelationRepository = cancelationRepository;
    }

    public AppointmentDto saveAppointmet(AppointmentDto appointmentDto){
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        Date appDate = Date.valueOf(appointmentDto.getDate());
        Date today = new Date(System.currentTimeMillis());
        if(appDate.before(today)){
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "The date must be greater than today");
        }
        appointmentEntity.setDate(Date.valueOf(appointmentDto.getDate()));
        appointmentEntity.setHour(Time.valueOf(appointmentDto.getTime()));
        appointmentEntity.setStatus(1);
        appointmentEntity.setVeterinary(veterinaryRepository.findById(appointmentDto.getVeterinary().getId()).orElseThrow(()->{throw new RuntimeException("Vet not found");}));
        appointmentEntity.setUser(userRepository.findById(appointmentDto.getUser().getId()).orElseThrow(()->{throw new RuntimeException("user not found");
        }));
        appointmentEntity.setCreated_at(new Timestamp(System.currentTimeMillis()));
        appointmentEntity = appointmentRepository.save(appointmentEntity);
        appointmentDto.setId(appointmentEntity.getId());
        return appointmentDto;
    }
    public List<ScheduleEntity> getAvailableSchedules(Integer veterinaryId, Date startDate, Date endDate){
        Calendar calendar = Calendar.getInstance();
        VeterinaryEntity veterinary = veterinaryRepository.findById(veterinaryId).orElseThrow(() -> new RuntimeException("Veterinary not found"));
        List<ScheduleEntity> availableSchedules = new ArrayList<>(veterinary.getSchedule());
        List<AppointmentEntity> appointments = appointmentRepository
                .getAppointmentEntitiesByVeterinaryAndDateGreaterThanEqualAndDateLessThanEqualAndStatus(veterinary, startDate, endDate,1);
        availableSchedules = availableSchedules.stream().map((schedule)->{
            for (AppointmentEntity appointment:appointments) {
                calendar.setTime(appointment.getDate());
//                logger.info("comparing hours {} =? {} ", appointment.getHour(), schedule.getHour());
//                logger.info("comparing dates {} =? {}", (calendar.get(Calendar.DAY_OF_WEEK)+6)%7,schedule.getDay()-1 );
                if ( Objects.equals(calendar.get(Calendar.DAY_OF_WEEK)-1, schedule.getDay()) && appointment.getHour().equals(schedule.getHour())) {
                    schedule.setAvaliable(2);
                    logger.info("update Status");
                    Date.valueOf("2022-10-14");
                };

            }
            return schedule;
        }).collect(Collectors.toList());

        return availableSchedules;

    }
    public Page<AppointmentDto> getAppointmentsByUser(Integer userId, Integer page, Integer size){

        Page<AppointmentEntity> appointments =
                appointmentRepository.getAppointmentEntitiesByUserId(userId, PageRequest.of(page, size, Sort.by(List.of(Sort.Order.desc("date"), Sort.Order.asc("hour")))));
        Page<AppointmentDto> appointmentDtos =  appointments.map((appointmentEntity -> {
            AppointmentDto appointmentDto = new AppointmentDto();
            appointmentDto.setId(appointmentEntity.getId());
            appointmentDto.setDate(appointmentEntity.getDate().toString());
            appointmentDto.setTime(appointmentEntity.getHour().toString());
            appointmentDto.setStatus(appointmentEntity.getStatus());
            VeterinaryDto veterinaryDto = new VeterinaryDto();
            veterinaryDto.setId(appointmentEntity.getVeterinary().getId());
            veterinaryDto.setName(appointmentEntity.getVeterinary().getName());
            veterinaryDto.setLastname(appointmentEntity.getVeterinary().getLastname());
            appointmentDto.setVeterinary(veterinaryDto);
            return appointmentDto;
        }));
        return appointmentDtos;
    }
    @Transactional
    public String cancelAppointment(CancelationEntity cancelationEntity){
        AppointmentEntity appointment = appointmentRepository.findById(cancelationEntity.getAppointment().getId()).orElseThrow(()->{throw new RuntimeException("Appointment not found");});
        appointment.setStatus(2);
        appointmentRepository.save(appointment);
        cancelationRepository.save(cancelationEntity);
        return "Appointment " + cancelationEntity.getId() +" canceled";
    }

}
