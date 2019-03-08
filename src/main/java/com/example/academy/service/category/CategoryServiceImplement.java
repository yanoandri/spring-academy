package com.example.academy.service.category;

import com.example.academy.model.custom.exception.DataValidationExceptionHandler;
import com.example.academy.model.entity.Category;
import com.example.academy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("CategoryServiceImplement")
public class CategoryServiceImplement implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> showCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setDescription(category.getDescription());
        newCategory.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        newCategory.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        categoryRepository.save(newCategory);
        return newCategory;
    }

    @Override
    public Category editCategory(Long id, Category category) {
        if(!categoryRepository.findById(id).isPresent())throw new DataValidationExceptionHandler("Category not found");
        Category editCategory = categoryRepository.findById(id).get();
        editCategory.setName(category.getName());
        editCategory.setDescription(category.getDescription());
        editCategory.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        categoryRepository.save(editCategory);
        return editCategory;
    }

    @Override
    public int deleteCategory(Long id) {
        if(!categoryRepository.findById(id).isPresent())throw new DataValidationExceptionHandler("Category not found");
        categoryRepository.deleteById(id);
        return 200;
    }
}
