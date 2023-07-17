package com.arqui.vetstore.dto;

public class OrderItemMapper {
    public static OrderItemDto orderItemEntityToDto(OrderItemEntity orderItemEntity) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setItem(ItemMapper.itemEntityToDto(orderItemEntity.getItem()));
        orderItemDto.setQuantity(orderItemEntity.getQuantity());
        return orderItemDto;
    }

public static OrderItemEntity orderItemDtoToEntity(OrderItemDto orderItemDto, OrderEntity orderEntity) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrder(orderEntity);
        orderItemEntity.setItem(ItemMapper.itemDtoToEntity(orderItemDto.getItem()));
        orderItemEntity.setQuantity(orderItemDto.getQuantity());
        return orderItemEntity;
    }
}
