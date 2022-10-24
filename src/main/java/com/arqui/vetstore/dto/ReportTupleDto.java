package com.arqui.vetstore.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class
ReportTupleDto implements Serializable {
    private String name;
    private Integer value;
    private Integer id;
    @Id
    private Integer number;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "ReportTupleDto{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", id=" + id +
                '}';
    }
}
