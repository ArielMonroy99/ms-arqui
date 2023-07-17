package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepostory extends JpaRepository<ScheduleEntity,Integer> {

}
