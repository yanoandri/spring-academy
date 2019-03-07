package com.example.academy.service.post;

import com.example.academy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImplement implements PostService {

    @Autowired
    private PostRepository postRepository;
}
