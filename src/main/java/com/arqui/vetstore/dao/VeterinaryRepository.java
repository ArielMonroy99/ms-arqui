package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.VeterinaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface VeterinaryRepository extends JpaRepository<VeterinaryEntity,Integer> {

    Page<VeterinaryEntity> findAllByStatus(Integer status, Pageable pageable);
}
