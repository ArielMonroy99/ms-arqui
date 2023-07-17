package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.ScheduleRepostory;
import com.arqui.vetstore.dto.ScheduleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScheduleBl {

   private ScheduleRepostory scheduleRepostory;
   public static final Logger logger = LoggerFactory.getLogger(ScheduleBl.class);

    @Autowired
    public ScheduleBl(ScheduleRepostory scheduleRepostory) {
        this.scheduleRepostory = scheduleRepostory;
    }

    public void saveAll(List<ScheduleEntity> entities){
        logger.info("Saving all schedules {}", entities);
        scheduleRepostory.saveAll(entities);
    }
}
