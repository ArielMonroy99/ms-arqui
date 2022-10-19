package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.ReportBl;
import com.arqui.vetstore.dto.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public Map<String,ReportDto> getReportProducts(){
        return reportBl.getReportProducts();
    }
}
