package com.example.academy.controller.user;

import com.example.academy.model.custom.MetaResponse;
import com.example.academy.model.custom.Response;
import com.example.academy.model.entity.User;
import com.example.academy.service.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(@Qualifier("UserServiceImplement") UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public Response getAllUser() {
        Response response = new Response();
        MetaResponse metaResponse = new MetaResponse();

        metaResponse.setCode(200);
        metaResponse.setMessage("success");
        metaResponse.setDebugInfo("success");

        response.setStatus(Response.MESSAGE_OK);
        response.setData(userService.getAllUsers());
        response.setMeta(metaResponse);

        return response;
    }
}
