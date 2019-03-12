package com.example.academy.service.post;

import com.example.academy.helper.Helper;
import com.example.academy.model.custom.exception.DataValidationExceptionHandler;
import com.example.academy.model.custom.exception.InputValidationExceptionHandler;
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
    public PaginateResponse showAllPost(int offset,int limit) {
        int totalData = postRepository.findAllByIsDeletedFalse().size();
        Pageable paginateElement = PageRequest.of(offset, limit, Sort.by("updatedDate").descending());
        List<Post> posts = postRepository.findAllByIsDeletedFalse(paginateElement);
        List<Object> datas = new ArrayList<>(posts);
        PaginateResponse result = new PaginateResponse();
        result.setTotalRow(totalData);
        result.setLists(datas);
        return result;
    }

    @Override
    public Post showDetailPost(Long id) {
        Post post = postRepository.findByIdAndIsDeletedFalse(id);
        if(post == null)throw new DataValidationExceptionHandler("Post not found");
        return post;
    }

    @Override
    public Post createPost(Post post) {
        if(!userRepository.findById(post.getUserId()).isPresent())throw new InputValidationExceptionHandler("User not found");
        if(categoryRepository.findByIdAndIsDeletedFalse(post.getCategoryId()) == null)throw new InputValidationExceptionHandler("Category not found");
        Post temp = new Post();
        temp.setTitle(post.getTitle());
        temp.setContent(post.getContent());
        temp.setCategoryId(post.getCategoryId());
        temp.setUserId(post.getUserId());
        temp.setPostSlug(Helper.toSlug(post.getTitle()));
        temp.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        temp.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        temp.setDeleted(false);
        postRepository.save(temp);
        return temp;
    }

    @Override
    public Post editPost(Long id, Post post) {
        if(!userRepository.findById(post.getUserId()).isPresent())throw new InputValidationExceptionHandler("User not found");
        if(categoryRepository.findByIdAndIsDeletedFalse(post.getCategoryId()) == null)throw new InputValidationExceptionHandler("Category not found");
        Post temp = postRepository.findByIdAndIsDeletedFalse(id);
        if(temp == null)throw new DataValidationExceptionHandler("Post not found");
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
        Post post = postRepository.findByIdAndIsDeletedFalse(id);
        if(post == null)throw new DataValidationExceptionHandler("Post not found");
        post.setDeleted(true);
        postRepository.save(post);
        return 200;
    }
}
