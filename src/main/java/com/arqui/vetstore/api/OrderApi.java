package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.OrderBl;
import com.arqui.vetstore.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public OrderDto getOrder(@RequestParam Integer id){
        return orderBl.getOrder(id);
    }
    @PostMapping
    @PreAuthorize("hasAnyRole( 'ROLE_USER',  'ROLE_USER')")
    public OrderDto saveOrder(@RequestBody OrderDto order){
        return orderBl.saveOrder(order);
    }
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public OrderDto updateOrder(@RequestBody OrderDto order){

        return orderBl.updateOrder(order);
    }
    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void deleteOrder(@RequestParam Integer id){
        orderBl.deleteOrder(id);
    }

    @GetMapping(path = "/active")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Page<OrderDto> getActiveOrders(@RequestParam Integer page, @RequestParam Integer size){
        return orderBl.getOrdersByStatus(page, size,1);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Page<OrderDto> getAllOrders(@RequestParam Integer page, @RequestParam Integer size){
        return orderBl.getAllOrders(page, size);
    }

    @PutMapping(path = "/{id}/status/{status}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public OrderDto updateOrderStatus(@PathVariable Integer id, @PathVariable Integer status){
        return orderBl.updateOrderStatus(id, status);
    }

}
