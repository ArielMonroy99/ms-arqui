package com.arqui.vetstore.dto;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto orderEntityToDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setDate(orderEntity.getDate());
        orderDto.setTotal(orderEntity.getTotal());
        orderDto.setUser(orderEntity.getUser().toDto());
        orderDto.setStatus(orderEntity.getStatus());
        orderDto.setAddress(AddressMapper.toAddressDto(orderEntity.getAddress()));
        orderDto.setItems(orderEntity.getItems().stream().map(OrderItemMapper::orderItemEntityToDto).collect(Collectors.toList()));
        return orderDto;
    }
}
