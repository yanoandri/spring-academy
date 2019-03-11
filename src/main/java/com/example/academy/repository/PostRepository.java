package com.example.academy.repository;

import com.example.academy.model.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Post findByIdAndIsDeletedFalse(Long id);

    public List<Post> findAllByIsDeletedFalse(Pageable pageable);

    public List<Post> findAllByIsDeletedFalse();
}
