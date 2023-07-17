package com.arqui.vetstore.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "category")
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String category;

    public CategoryEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
