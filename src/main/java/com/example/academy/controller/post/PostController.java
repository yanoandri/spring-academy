package com.example.academy.controller.post;

import com.example.academy.model.custom.request.PaginateRequest;
import com.example.academy.model.custom.response.MetaResponse;
import com.example.academy.model.custom.response.PaginateResponse;
import com.example.academy.model.custom.response.Response;
import com.example.academy.model.entity.Post;
import com.example.academy.service.post.PostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;

    public PostController(@Qualifier("PostServiceImplement") PostService postService){
        this.postService = postService;
    }

    @PostMapping(value = "/show/all")
    public ResponseEntity showAllPost(@RequestBody PaginateRequest paginateRequest){
        Response response = new Response();
        PaginateResponse paginateResponse = postService.showAllPost(paginateRequest);
        response.setStatus(Response.MESSAGE_OK);
        response.setData(paginateResponse);
        response.setMeta(new MetaResponse(HttpStatus.OK.value(), "success", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/show/{id}")
    public ResponseEntity showDetailPost(@PathVariable("id") Long id){
        Response response = new Response();
        Post post = postService.showDetailPost(id);
        response.setStatus(Response.MESSAGE_OK);
        response.setData(post);
        response.setMeta(new MetaResponse(HttpStatus.OK.value(), "success", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity addPost(@RequestBody Post post){
        Response response = new Response();
        Post data = postService.createPost(post);
        response.setStatus(Response.MESSAGE_OK);
        response.setData(data);
        response.setMeta(new MetaResponse(HttpStatus.CREATED.value(), "Post has been added!", ""));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/edit/{id}")
    public ResponseEntity editPost(@PathVariable("id") Long id, @RequestBody Post post){
        Response response = new Response();
        Post data = postService.editPost(id, post);
        response.setStatus(Response.MESSAGE_OK);
        response.setData(data);
        response.setMeta(new MetaResponse(HttpStatus.CREATED.value(), "Post has been updated!", ""));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity deletePost(@PathVariable("id") Long id){
        Response response = new Response();
        int isSuccess = postService.deletePost(id);
        response.setStatus(Response.MESSAGE_OK);
        response.setMeta(new MetaResponse(isSuccess, "Post has been deleted!", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
