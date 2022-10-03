package com.arqui.vetstore.dao;

import com.arqui.vetstore.bl.ItemBl;
import com.arqui.vetstore.dto.entity.CategoryEntity;
import com.arqui.vetstore.dto.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    Page<ItemEntity> findAllByStatus(Integer status, Pageable pageable);
    Page<ItemEntity> findAllByStatusAndNameContaining(Integer status, String name, Pageable pageable);
    Page<ItemEntity> findAllByStatusAndCategory(Integer status, CategoryEntity category, Pageable pageable);
}
