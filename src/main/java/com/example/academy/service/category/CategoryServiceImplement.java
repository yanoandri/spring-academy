package com.example.academy.service.category;

import com.example.academy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImplement implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
}
