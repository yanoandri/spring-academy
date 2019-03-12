package com.example.academy.controller.user;

import com.example.academy.model.custom.response.MetaResponse;
import com.example.academy.model.custom.response.Response;
import com.example.academy.model.entity.User;
import com.example.academy.service.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(@Qualifier("UserServiceImplement") UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody User user){
        Response response = new Response();
        User loginUser = userService.login(user);
        response.setStatus(Response.MESSAGE_OK);
        response.setData(loginUser);
        response.setMeta(new MetaResponse(HttpStatus.OK.value(), "Login success", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/logout/{id}")
    public ResponseEntity logout(@PathVariable("id") Long id){
        Response response = new Response();
        int isSuccess = userService.logout(id);
        response.setStatus(Response.MESSAGE_OK);
        response.setMeta(new MetaResponse(isSuccess, "Logout success", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody User user){
        Response response = new Response();
        int isSuccess = userService.registerUsers(user);
        response.setStatus(Response.MESSAGE_OK);
        response.setMeta(new MetaResponse(isSuccess, "Register user success", ""));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/reset/password")
    public ResponseEntity resetPassword(@RequestBody User user){
        Response response = new Response();
        int isSuccess = userService.resetPassword(user);
        response.setStatus(Response.MESSAGE_OK);
        response.setMeta(new MetaResponse(isSuccess, "Reset password success", ""));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/edit/{id}")
    public ResponseEntity editUser(@PathVariable("id") Long id, @RequestBody User user){
        Response response = new Response();
        User updatedUser = userService.editUser(id,user);
        response.setStatus(Response.MESSAGE_OK);
        response.setData(updatedUser);
        response.setMeta(new MetaResponse(HttpStatus.OK.value(), "Update profile success", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/show/profile/{id}")
    public ResponseEntity showProfile(@PathVariable("id") Long id){
        Response response = new Response();
        User user = userService.showProfile(id);
        response.setStatus(Response.MESSAGE_OK);
        response.setData(user);
        response.setMeta(new MetaResponse(HttpStatus.OK.value(), "Data found", ""));
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
