package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.ReportDto;
import com.arqui.vetstore.dto.ReportTupleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportTupleDto,Long> {
    @Query(nativeQuery = true,
    value = "Select sum(item_order .quantity) as value, extract(Month from o.date) as name, i.id as id \n" +
            "from item_order join item i on item_order.item_id = i.id join orders o on item_order.order_id = o.id \n" +
            "group by extract(Month from o.date), i.id")
    List<ReportTupleDto> getReportProducts();

    @Query(value = "select count(appointment.id) as value, extract(Month from appointment.date) as name from appointment join veterinary v\n" +
            "    on appointment.veterinary_id = v.id group by extract(Month from appointment.date), v.id"
        ,nativeQuery = true)
    List<ReportTupleDto> getReportVeterinaries();

}
