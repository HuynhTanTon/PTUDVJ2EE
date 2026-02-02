package com.example.bai4.service;

import com.example.bai4.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private List<Category> listCategory = new ArrayList<>();
    
    public CategoryService() {
        // Khá»Ÿi táº¡o dá»¯ liá»‡u máº«u
        listCategory.add(new Category(1, "Điện thoại"));
        listCategory.add(new Category(2, "Laptop"));
    }
    
    public List<Category> getAll() {
        return listCategory;
    }
    
    public Category get(int id) {
        return listCategory.stream()
                .filter(category -> category.getId() == id)
                .findFirst()
                .orElse(null);
    }
}