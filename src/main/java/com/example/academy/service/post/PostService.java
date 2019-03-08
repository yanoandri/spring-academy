package com.example.academy.service.post;

import com.example.academy.model.custom.request.PaginateRequest;
import com.example.academy.model.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> showAllPost(PaginateRequest paginateRequest);
}
