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
        return categoryRepository.findAllByIsDeletedFalse();
    }

    @Override
    public Category addCategory(Category category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setDescription(category.getDescription());
        newCategory.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        newCategory.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        newCategory.setDeleted(false);
        categoryRepository.save(newCategory);
        return newCategory;
    }

    @Override
    public Category editCategory(Long id, Category category) {
        Category editCategory = categoryRepository.findByIdAndIsDeletedFalse(id);
        if(editCategory == null)throw new DataValidationExceptionHandler("Category not found");
        editCategory.setName(category.getName());
        editCategory.setDescription(category.getDescription());
        editCategory.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        categoryRepository.save(editCategory);
        return editCategory;
    }

    @Override
    public int deleteCategory(Long id) {
        Category category = categoryRepository.findByIdAndIsDeletedFalse(id);
        if(category == null)throw new DataValidationExceptionHandler("Category not found");
        category.setDeleted(true);
        categoryRepository.save(category);
        return 200;
    }
}
