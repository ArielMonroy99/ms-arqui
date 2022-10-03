package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

}
