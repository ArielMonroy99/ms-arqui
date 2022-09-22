package com.arqui.vetstore.dto.entity;

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
    @NotNull
    private Integer veterinary_id;

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

    public Integer getVeterinary_id() {
        return veterinary_id;
    }

    public void setVeterinary_id(Integer veterinary_id) {
        this.veterinary_id = veterinary_id;
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
                ", day=" + day +
                ", hour=" + hour +
                ", avaliable=" + avaliable +
                ", veterinary_id=" + veterinary_id +
                '}';
    }
}
