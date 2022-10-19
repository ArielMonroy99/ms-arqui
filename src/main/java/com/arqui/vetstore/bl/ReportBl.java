package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.ItemRepository;
import com.arqui.vetstore.dao.ReportRepository;
import com.arqui.vetstore.dao.VeterinaryRepository;
import com.arqui.vetstore.dto.ReportDto;
import com.arqui.vetstore.dto.ReportTupleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportBl {

    private final ReportRepository reportRepository;
    private final VeterinaryRepository veterinaryRepository;
    private final ItemRepository itemRepository;

    public static final Logger logger = LoggerFactory.getLogger(ReportBl.class);
    @Autowired
    public ReportBl(ReportRepository reportRepository, VeterinaryRepository veterinaryRepository, ItemRepository itemRepository) {
        this.reportRepository = reportRepository;
        this.veterinaryRepository = veterinaryRepository;
        this.itemRepository = itemRepository;
    }

    public Map<String,ReportDto> getReportProducts(){
        List<ReportTupleDto> reportTupleDtos = reportRepository.getReportProducts();
       logger.info("reportTupleDtos {}",reportTupleDtos);

        Map<String, ReportDto> reportDtoMap = new HashMap<>();
        for (ReportTupleDto reportTupleDto : reportTupleDtos) {
            String name = itemRepository.findById(reportTupleDto.getId()).orElseThrow(
                    () -> new RuntimeException("Item not found")).getName();
            reportTupleDto.setName(months().get(Integer.parseInt(reportTupleDto.getName())));
            if(reportDtoMap.containsKey(name)){
                reportDtoMap.get(name).getSeries().add(reportTupleDto);
            }else{
                ReportDto reportDto = new ReportDto();
                reportDto.setName(name);
                List<ReportTupleDto> series = new ArrayList<>();
                series.add(reportTupleDto);
                reportDto.setSeries(series);
                reportDtoMap.put(reportDto.getName(),reportDto);
            }
        }
        logger.info("reportTupleDtos {}",reportTupleDtos);
        return reportDtoMap;
    }

    public static Map<Integer,String> months(){
       Map<Integer,String> months = new HashMap<>();
           months.put(1,"January");
           months.put(2,"February");
           months.put(3,"March");
           months.put(4,"April");
           months.put(5,"May");
           months.put(6,"June");
           months.put(7,"July");
           months.put(8,"August");
           months.put(9,"September");
           months.put(10,"October");
           months.put(11,"November");
           months.put(12,"December");
           return months;
    }
}
