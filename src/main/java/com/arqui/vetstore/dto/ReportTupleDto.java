package com.arqui.vetstore.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportTupleDto {
    private String name;
    private Integer value;
    @Id
    private Integer id;

    public ReportTupleDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ReportTupleDto{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
