package com.arqui.vetstore.api;

import com.arqui.vetstore.bl.CategoryBl;
import com.arqui.vetstore.dto.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryApi {
    private CategoryBl categoryBl;
    @Autowired
    public CategoryApi(CategoryBl categoryBl) {
        this.categoryBl = categoryBl;
    }
    @GetMapping
    public List<CategoryEntity> getAllCategories(){
        return categoryBl.getCategories();
    }

}
