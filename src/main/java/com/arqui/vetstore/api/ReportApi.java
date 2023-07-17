package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.ReportBl;
import com.arqui.vetstore.dto.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportApi {
    private ReportBl reportBl;
    @Autowired
    public ReportApi(ReportBl reportBl) {
        this.reportBl = reportBl;
    }

    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<Integer,ReportDto> getReportProducts(@RequestParam Integer year){
        return reportBl.getReportProductsLineChart(year);
    }

    @GetMapping("/vets")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<Integer,ReportDto> getReportVeterinaries(@RequestParam Integer year){
        return reportBl.getReportVeterinaryLineChart(year);
    }
}
