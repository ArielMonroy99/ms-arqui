package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    Page<OrderEntity> findAllByUserIdAndStatus(Pageable pageable, Integer userId, Integer status);
    Page<OrderEntity> findAllByStatusOrderByDateDesc(Pageable pageable, Integer status);
}
