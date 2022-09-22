package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepostory extends JpaRepository<ScheduleEntity,Integer> {

}
