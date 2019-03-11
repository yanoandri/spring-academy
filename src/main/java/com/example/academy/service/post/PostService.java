package com.example.academy.service.post;

import com.example.academy.model.custom.response.PaginateResponse;
import com.example.academy.model.entity.Post;

public interface PostService {
    PaginateResponse showAllPost(int offset, int limit);
    Post showDetailPost(Long id);
    Post createPost(Post post);
    Post editPost(Long id,Post post);
    int deletePost(Long id);
}
