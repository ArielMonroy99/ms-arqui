package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.AppointmentRepository;
import com.arqui.vetstore.dao.CancelationRepository;
import com.arqui.vetstore.dao.UserRepository;
import com.arqui.vetstore.dao.VeterinaryRepository;
import com.arqui.vetstore.dto.AppointmentDto;
import com.arqui.vetstore.dto.VeterinaryDto;
import com.arqui.vetstore.dto.entity.AppointmentEntity;
import com.arqui.vetstore.dto.entity.CancelationEntity;
import com.arqui.vetstore.dto.entity.ScheduleEntity;
import com.arqui.vetstore.dto.entity.VeterinaryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
                .getAppointmentEntitiesByVeterinaryAndDateGreaterThanEqualAndDateLessThanEqual(veterinary, startDate, endDate);
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
    public List<AppointmentDto> getAppointmentsByUser(Integer userId){
        List<AppointmentEntity> appointments = appointmentRepository.getAppointmentEntitiesByUserIdAndStatus(userId,1);
        return appointments.stream().map((appointment)->{
            AppointmentDto appointmentDto = new AppointmentDto();
            appointmentDto.setId(appointment.getId());
            appointmentDto.setDate(appointment.getDate().toString());
            appointmentDto.setTime(appointment.getHour().toString());
            appointmentDto.setStatus(appointment.getStatus());
            VeterinaryDto veterinaryDto = new VeterinaryDto();
            veterinaryDto.setId(appointment.getVeterinary().getId());
            veterinaryDto.setName(appointment.getVeterinary().getName());
            veterinaryDto.setLastname(appointment.getVeterinary().getLastname());
            veterinaryDto.setPhone(appointment.getVeterinary().getPhone());
            appointmentDto.setVeterinary(veterinaryDto);
            appointmentDto.setUser(appointment.getUser().toDto());
            return appointmentDto;
        }).collect(Collectors.toList());
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
