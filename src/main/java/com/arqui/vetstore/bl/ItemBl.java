package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.CategoryRepository;
import com.arqui.vetstore.dao.ItemRepository;
import com.arqui.vetstore.dto.ItemDto;
import com.arqui.vetstore.dto.CategoryEntity;
import com.arqui.vetstore.dto.ItemEntity;
import com.arqui.vetstore.dto.ItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class ItemBl {
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;

    public static final Logger logger = LoggerFactory.getLogger(ItemBl.class);
    @Autowired
    public ItemBl(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    public ItemDto saveItem(ItemDto item){
        logger.info("Saving item");
        ItemEntity itemEntity = ItemMapper.itemDtoToEntity(item);
        itemEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        itemEntity.setStatus(1);
        itemEntity = itemRepository.save(itemEntity);
        item.setId(itemEntity.getId());

        return item;
    }

    public ItemDto updateItem(ItemDto item){
        ItemEntity itemEntity = itemRepository.findById(item.getId()).orElseThrow(()-> new RuntimeException("Item not found"));
        itemEntity.setName(item.getName());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setStock(item.getStock());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setCategory(item.getCategory());
        itemEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        itemEntity = itemRepository.save(itemEntity);
        return item;
    }

    public Page<ItemDto> getItems(Integer size , Integer page, String sort, String sortDir){
        if(sortDir == null) sortDir = "asc";
        if(sort == null) sort = "id";
        Page<ItemEntity> items = itemRepository.findAllByStatus(1,PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort));
        return items.map(ItemMapper::itemEntityToDto);
    }

    public ItemDto getItem(Integer id){
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(()-> new RuntimeException("Item not found"));
        return ItemMapper.itemEntityToDto(itemEntity);
    }

    public void deleteItem(Integer id){
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        itemEntity.setStatus(0);
        itemRepository.save(itemEntity);
    }

    public Page<ItemDto> getItemsByCategory(Integer size , Integer page, Integer categoryId){
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
        Page<ItemEntity> items = itemRepository.findAllByStatusAndCategory(1, categoryEntity, PageRequest.of(page, size));
        return items.map(ItemMapper::itemEntityToDto);
    }
}
