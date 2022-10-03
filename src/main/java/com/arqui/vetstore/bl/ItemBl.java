package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.CategoryRepository;
import com.arqui.vetstore.dao.ItemRepository;
import com.arqui.vetstore.dto.ItemDto;
import com.arqui.vetstore.dto.entity.CategoryEntity;
import com.arqui.vetstore.dto.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
@Service
public class ItemBl {
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public ItemBl(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    public ItemDto saveItem(ItemDto item){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(item.getName());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setStock(item.getStock());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setCategory(item.getCategory());
        itemEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        itemEntity.setStatus(1);
        itemEntity = itemRepository.save(itemEntity);
        item.setId(itemEntity.getId());

        return item;
    }

    public ItemDto updateItem(ItemDto item){
        ItemEntity itemEntity = itemRepository.findById(item.getId()).get();
        itemEntity.setName(item.getName());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setStock(item.getStock());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setCategory(item.getCategory());
        itemEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        itemEntity.setStatus(1);
        itemEntity = itemRepository.save(itemEntity);
        item.setId(itemEntity.getId());

        return item;
    }

    public Page<ItemDto> getItems(Integer size , Integer page){
        Page<ItemEntity> items = itemRepository.findAllByStatus(1,PageRequest.of(page, size));
        Page<ItemDto> itemDtos = items.map(itemEntity -> {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(itemEntity.getId());
            itemDto.setName(itemEntity.getName());
            itemDto.setDescription(itemEntity.getDescription());
            itemDto.setPrice(itemEntity.getPrice());
            itemDto.setStock(itemEntity.getStock());
            itemDto.setCategory(itemEntity.getCategory());
            return itemDto;
        });
        return itemDtos;
    }

    public ItemDto getItem(Integer id){
        ItemEntity itemEntity = itemRepository.findById(id).get();
        ItemDto itemDto = new ItemDto();
        itemDto.setId(itemEntity.getId());
        itemDto.setName(itemEntity.getName());
        itemDto.setDescription(itemEntity.getDescription());
        itemDto.setPrice(itemEntity.getPrice());
        itemDto.setStock(itemEntity.getStock());
        itemDto.setCategory(itemEntity.getCategory());
        return itemDto;
    }

    public void deleteItem(Integer id){
        ItemEntity itemEntity = itemRepository.findById(id).get();
        itemEntity.setStatus(0);
        itemRepository.save(itemEntity);
    }

    public Page<ItemDto> getItemsByCategory(Integer size , Integer page, Integer categoryId){
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
        Page<ItemEntity> items = itemRepository.findAllByStatusAndCategory(1, categoryEntity, PageRequest.of(page, size));
        Page<ItemDto> itemDtos = items.map(itemEntity -> {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(itemEntity.getId());
            itemDto.setName(itemEntity.getName());
            itemDto.setDescription(itemEntity.getDescription());
            itemDto.setPrice(itemEntity.getPrice());
            itemDto.setStock(itemEntity.getStock());
            itemDto.setCategory(itemEntity.getCategory());
            return itemDto;
        });
        return itemDtos;
    }
}
