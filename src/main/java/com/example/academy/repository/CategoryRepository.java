package com.example.academy.repository;

import com.example.academy.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findAllByIsDeletedFalse();

    public Category findByIdAndIsDeletedFalse(Long id);
}
