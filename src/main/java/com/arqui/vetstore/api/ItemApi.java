package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.ItemBl;
import com.arqui.vetstore.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ItemDto saveItem(@RequestBody ItemDto item){
        return itemBl.saveItem(item);
    }
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ItemDto updateItem(@RequestBody ItemDto item){
        return itemBl.updateItem(item);
    }
    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable Integer id){
        return itemBl.getItem(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void deleteItem(@PathVariable Integer id){
        itemBl.deleteItem(id);
    }
 }
