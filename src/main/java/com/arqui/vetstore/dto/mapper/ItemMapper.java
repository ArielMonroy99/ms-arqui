package com.arqui.vetstore.dto.mapper;

import com.arqui.vetstore.dto.ItemDto;
import com.arqui.vetstore.dto.entity.ItemEntity;

public class ItemMapper {
    public static ItemDto itemEntityToDto(ItemEntity itemEntity) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(itemEntity.getId());
        itemDto.setName(itemEntity.getName());
        itemDto.setPrice(itemEntity.getPrice());
        itemDto.setStock(itemEntity.getStock());
        itemDto.setDescription(itemEntity.getDescription());
        itemDto.setCategory(itemEntity.getCategory());
        return itemDto;
    }

    public static ItemEntity itemDtoToEntity(ItemDto itemDto) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemDto.getId());
        itemEntity.setName(itemDto.getName());
        itemEntity.setPrice(itemDto.getPrice());
        itemEntity.setStock(itemDto.getStock());
        itemEntity.setCategory(itemDto.getCategory());
        return itemEntity;
    }
}
