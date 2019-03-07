package com.example.academy.controller.post;

import com.example.academy.service.post.PostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class PostController {
    private PostService postService;

    public PostController(@Qualifier("PostServiceImplement") PostService postService){
        this.postService = postService;
    }
}
