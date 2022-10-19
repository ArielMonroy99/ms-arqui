package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.ItemBl;
import com.arqui.vetstore.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;

@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "*")
public class ItemApi {
    private ItemBl itemBl;
    @Autowired
    public ItemApi(ItemBl itemBl) {
        this.itemBl = itemBl;
    }

    @GetMapping
    public Page<ItemDto> getItems(@RequestParam Integer size,
                                  @RequestParam Integer page,
                                  @RequestParam(required = false) String sort,
                                  @RequestParam(required = false) String sortDir){
        return itemBl.getItems(size, page, sort, sortDir);
    }
    @PostMapping
    public ItemDto saveItem(@RequestBody ItemDto item){
        return itemBl.saveItem(item);
    }
    @PutMapping
    public ItemDto updateItem(@RequestBody ItemDto item){
        return itemBl.updateItem(item);
    }
    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable Integer id){
        return itemBl.getItem(id);
    }
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Integer id){
        itemBl.deleteItem(id);
    }
 }
