package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.OrderBl;
import com.arqui.vetstore.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderApi{
    private final OrderBl orderBl;
    @Autowired
    public OrderApi(OrderBl orderBl) {
        this.orderBl = orderBl;
    }

    @GetMapping
    public Page<OrderDto> getOrders(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Integer userId){
        return orderBl.getOrders(page, size, userId);
    }

    @GetMapping(path = "/{id}")
    public OrderDto getOrder(@RequestParam Integer id){
        return orderBl.getOrder(id);
    }

    @PostMapping
    public OrderDto saveOrder(@RequestBody OrderDto order){
        return orderBl.saveOrder(order);
    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto order){
        return orderBl.updateOrder(order);
    }

    @DeleteMapping
    public void deleteOrder(@RequestParam Integer id){
        orderBl.deleteOrder(id);
    }
}
