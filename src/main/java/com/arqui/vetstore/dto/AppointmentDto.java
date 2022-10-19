package com.arqui.vetstore.dto;

public class AppointmentDto {
    private Integer id;
    private String date;
    private String time;
    private VeterinaryDto veterinary;
    private UserDto user;

    private Integer status;

    public AppointmentDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public VeterinaryDto getVeterinary() {
        return veterinary;
    }

    public void setVeterinary(VeterinaryDto veterinary) {
        this.veterinary = veterinary;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", veterinary=" + veterinary +
                ", user=" + user +
                ", status=" + status +
                '}';
    }
}
