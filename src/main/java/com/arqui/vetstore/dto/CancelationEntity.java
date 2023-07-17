package com.arqui.vetstore.dto;

import javax.persistence.*;

@Table(name = "cancelation")
@Entity(name = "cancelation")
public class CancelationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private AppointmentEntity appointment;
    private String reason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentEntity appointment) {
        this.appointment = appointment;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "CancelationEntity{" +
                "id=" + id +
                ", appointmentId=" + appointment +
                ", reason='" + reason + '\'' +
                '}';
    }
}
