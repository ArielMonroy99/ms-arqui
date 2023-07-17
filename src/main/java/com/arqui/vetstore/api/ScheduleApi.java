package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.ScheduleBl;
import com.arqui.vetstore.dto.ScheduleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleApi {

    private ScheduleBl scheduleBl;
    public static Logger logger = LoggerFactory.getLogger(ScheduleApi.class);
    @Autowired
    public ScheduleApi(ScheduleBl scheduleBl) {
        this.scheduleBl = scheduleBl;
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void save(@RequestBody List<ScheduleEntity> entities){

        logger.info("Saving all schedules {}", entities);
        scheduleBl.saveAll(entities);
    }
}
