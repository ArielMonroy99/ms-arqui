package com.arqui.vetstore.dao;

import com.arqui.vetstore.dto.ReportTupleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportTupleDto,Integer> {
    @Query(nativeQuery = true,
    value = "Select sum(item_order .quantity) as value, extract(Month from o.date) as name, i.id as id , row_number() over () as number \n" +
            "from item_order join item i on item_order.item_id = i.id join orders o on item_order.order_id = o.id " +
            "where extract(Year from o.date) = :year " +
            "group by extract(Month from o.date), i.id order by extract(Month from o.date), i.id ")
    List<ReportTupleDto> getReportProductsByYear(@Param("year") Integer year);

    @Query(value = "select count(appointment.id) as value, extract(Month from appointment.date) as name, v.id as id , row_number() over () as number \n" +
            "            from appointment  join  veterinary v on appointment.veterinary_id = v.id \n" +
            "            where appointment.status != 3 and extract(Year from appointment.date) = :year \n" +
            "            group by extract(Month from appointment.date), v.id order by extract(Month from appointment.date), v.id" ,nativeQuery = true)
    List<ReportTupleDto> getReportVeterinariesByYear(@Param("year") Integer year);

}
