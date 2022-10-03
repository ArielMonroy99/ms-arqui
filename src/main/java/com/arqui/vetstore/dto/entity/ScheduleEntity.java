package com.arqui.vetstore.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
@Table(name = "schedule")
public class ScheduleEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer day;
    @NotNull
    private Time hour;
    @NotNull
    private Integer avaliable;
    @ManyToOne
    @JoinColumn(name = "veterinary_id")
    @JsonIgnore
    private VeterinaryEntity veterinary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public Integer getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(Integer avaliable) {
        this.avaliable = avaliable;
    }

    public VeterinaryEntity getVeterinary() {
        return veterinary;
    }

    public void setVeterinary(VeterinaryEntity veterinary) {
        this.veterinary = veterinary;
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
                ", day=" + day +
                ", hour=" + hour +
                ", avaliable=" + avaliable +
                ", veterinary_id=" + veterinary.getId() +
                '}';
    }
}
