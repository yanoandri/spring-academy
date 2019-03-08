package com.example.academy.service.post;

import com.example.academy.helper.Helper;
import com.example.academy.model.custom.exception.DataValidationExceptionHandler;
import com.example.academy.model.custom.exception.InputValidationExceptionHandler;
import com.example.academy.model.custom.request.PaginateRequest;
import com.example.academy.model.custom.response.PaginateResponse;
import com.example.academy.model.entity.Post;
import com.example.academy.repository.CategoryRepository;
import com.example.academy.repository.PostRepository;
import com.example.academy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service("PostServiceImplement")
public class PostServiceImplement implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PaginateResponse showAllPost(PaginateRequest paginateRequest) {
        int totalData = postRepository.findAll().size();
        Pageable paginateElement = PageRequest.of(paginateRequest.getOffset(),paginateRequest.getLimit(), Sort.by("updatedDate").descending());
        List<Post> posts = postRepository.findAll(paginateElement).getContent();
        List<Object> datas = new ArrayList<>(posts);
        PaginateResponse result = new PaginateResponse();
        result.setTotalRow(totalData);
        result.setLists(datas);
        return result;
    }

    @Override
    public Post showDetailPost(Long id) {
        if(!postRepository.findById(id).isPresent())throw new DataValidationExceptionHandler("Post not found");
        return postRepository.findById(id).get();
    }

    @Override
    public Post createPost(Post post) {
        if(!userRepository.findById(post.getUserId()).isPresent())throw new InputValidationExceptionHandler("User not found");
        if(!categoryRepository.findById(post.getCategoryId()).isPresent())throw new InputValidationExceptionHandler("Category not found");
        Post temp = new Post();
        temp.setTitle(post.getTitle());
        temp.setContent(post.getContent());
        temp.setCategoryId(post.getCategoryId());
        temp.setUserId(post.getUserId());
        temp.setPostSlug(Helper.toSlug(post.getTitle()));
        temp.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        temp.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        postRepository.save(temp);
        return temp;
    }

    @Override
    public Post editPost(Long id, Post post) {
        if(!userRepository.findById(post.getUserId()).isPresent())throw new InputValidationExceptionHandler("User not found");
        if(!categoryRepository.findById(post.getCategoryId()).isPresent())throw new InputValidationExceptionHandler("Category not found");
        if(!postRepository.findById(id).isPresent())throw new DataValidationExceptionHandler("Post not found");
        Post temp = postRepository.findById(id).get();
        temp.setTitle(post.getTitle());
        temp.setContent(post.getContent());
        temp.setCategoryId(post.getCategoryId());
        temp.setUserId(post.getUserId());
        temp.setPostSlug(Helper.toSlug(post.getTitle()));
        temp.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        postRepository.save(temp);
        return temp;
    }

    @Override
    public int deletePost(Long id) {
        if(!postRepository.findById(id).isPresent())throw new DataValidationExceptionHandler("Post not found");
        postRepository.deleteById(id);
        return 200;
    }
}
