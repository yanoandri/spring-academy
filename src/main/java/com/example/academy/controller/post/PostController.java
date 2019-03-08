package com.example.academy.controller.post;

import com.example.academy.model.custom.request.PaginateRequest;
import com.example.academy.model.custom.response.MetaResponse;
import com.example.academy.model.custom.response.Response;
import com.example.academy.model.entity.Post;
import com.example.academy.service.post.PostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Post> posts = postService.showAllPost(paginateRequest);
        response.setStatus(Response.MESSAGE_OK);
        response.setData(posts);
        response.setMeta(new MetaResponse(HttpStatus.OK.value(), "success", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
