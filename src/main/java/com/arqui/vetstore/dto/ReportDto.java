package com.arqui.vetstore.dto;

import java.util.List;

public class ReportDto {
    private String name;
    private List<ReportTupleDto> series;

    public ReportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReportTupleDto> getSeries() {
        return series;
    }

    public void setSeries(List<ReportTupleDto> series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                "name='" + name + '\'' +
                ", series=" + series +
                '}';
    }
}
