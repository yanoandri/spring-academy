package com.example.academy.service.post;

import com.example.academy.model.custom.request.PaginateRequest;
import com.example.academy.model.custom.response.PaginateResponse;
import com.example.academy.model.entity.Post;

public interface PostService {
    PaginateResponse showAllPost(PaginateRequest paginateRequest);
    Post showDetailPost(Long id);
    Post createPost(Post post);
    Post editPost(Long id,Post post);
    int deletePost(Long id);
}
