package com.arqui.vetstore.dto.entity;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Table(name = "appointment")
@Entity(name = "appointment")
public class AppointmentEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private Date date;
    private Time hour;
    @OneToOne
    private VeterinaryEntity veterinary;
    @OneToOne
    private UserEntity user;
    private Integer status;
    private Timestamp created_at;
    private Timestamp updated_at;

    public AppointmentEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public VeterinaryEntity getVeterinary() {
        return veterinary;
    }

    public void setVeterinary(VeterinaryEntity veterinary) {
        this.veterinary = veterinary;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentEntity that = (AppointmentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(hour, that.hour) && Objects.equals(veterinary, that.veterinary) && Objects.equals(user, that.user) && Objects.equals(status, that.status) && Objects.equals(created_at, that.created_at) && Objects.equals(updated_at, that.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, hour, veterinary, user, status, created_at, updated_at);
    }
}
