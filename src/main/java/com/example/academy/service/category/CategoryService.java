package com.example.academy.service.category;

import com.example.academy.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> showCategory();
    Category addCategory(Category category);
    Category editCategory(Long id,Category category);
    int deleteCategory(Long id);
}
