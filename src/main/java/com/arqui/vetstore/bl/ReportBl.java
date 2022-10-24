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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<Integer,ReportDto> getReportProductsLineChart(Integer year){
        List<ReportTupleDto> reportTupleDtos = reportRepository.getReportProductsByYear(year);
        logger.info("reportTupleDtos {}",reportTupleDtos);
        Map<Integer, ReportDto> reportDtoMap = new HashMap<>();
        String month = "";
        logger.info("reportTuple length {}", reportTupleDtos.size());
        int counter = 0;
        for(ReportTupleDto touple : reportTupleDtos){
            logger.info("touple {}", touple);
            counter++;
            touple.setName(months().get(Integer.parseInt(touple.getName())));
            if(!reportDtoMap.containsKey(touple.getId())){
                ReportDto reportDto = new ReportDto();
                reportDto.setName(itemRepository.findById(touple.getId()).orElseThrow().getName());
                reportDtoMap.put(touple.getId(),reportDto);
            }
            ReportDto report = reportDtoMap.get(touple.getId());
            if(report.getSeries()==null){
                report.setSeries(new ArrayList<>());
            }
            report.getSeries().add(touple);
        }
        return reportDtoMap;
    }
    public Map<Integer,ReportDto> getReportVeterinaryLineChart(Integer year){
        List<ReportTupleDto> reportTupleDtos = reportRepository.getReportVeterinariesByYear(year);
        logger.info("reportTupleDtos {}",reportTupleDtos);
        Map<Integer, ReportDto> reportDtoMap = new HashMap<>();
        String month = "";
        logger.info("reportTuple length {}", reportTupleDtos.size());
        int counter = 0;
        for(ReportTupleDto touple : reportTupleDtos){
            logger.info("touple {}", touple);
            counter++;
            touple.setName(months().get(Integer.parseInt(touple.getName())));
            if(!reportDtoMap.containsKey(touple.getId())){
                ReportDto reportDto = new ReportDto();
                
                reportDto.setName(veterinaryRepository.findById(touple.getId()).orElseThrow().getName());
                reportDtoMap.put(touple.getId(),reportDto);
            }
            ReportDto report = reportDtoMap.get(touple.getId());
            if(report.getSeries()==null){
                report.setSeries(new ArrayList<>());
            }
            report.getSeries().add(touple);
        }
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
