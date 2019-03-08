package com.example.academy.service.user;

import com.example.academy.model.entity.User;

public interface UserService {
    int registerUsers(User user);
    User login(User user);
    int resetPassword(User user);
    int editUser(Long id,User user);
    int logout(Long id);
    User showProfile(Long id);
}
