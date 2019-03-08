package com.example.academy.service.post;

import com.example.academy.model.custom.request.PaginateRequest;
import com.example.academy.model.entity.Post;
import com.example.academy.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PostServiceImplement")
public class PostServiceImplement implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> showAllPost(PaginateRequest paginateRequest) {
        Pageable paginateElement = PageRequest.of(paginateRequest.getOffset(),paginateRequest.getLimit(), Sort.by("updatedDate").descending());
        List<Post> posts = postRepository.findAll(paginateElement).getContent();
        return posts;
    }
}
