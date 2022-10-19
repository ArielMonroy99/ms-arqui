package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.entity.CancelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancelationRepository extends JpaRepository<CancelationEntity, Integer> {

}
