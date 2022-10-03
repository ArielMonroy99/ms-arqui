package com.arqui.vetstore.bl;

import com.arqui.vetstore.dao.CategoryRepository;
import com.arqui.vetstore.dto.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBl {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryBl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> getCategories(){
        return categoryRepository.findAll();
    }
}
