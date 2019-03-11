package com.example.academy.service.user;

import com.example.academy.helper.Helper;
import com.example.academy.model.custom.exception.DataValidationExceptionHandler;
import com.example.academy.model.custom.exception.InputValidationExceptionHandler;
import com.example.academy.model.entity.User;
import com.example.academy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserServiceImplement")
public class UserServiceImplement implements UserService {
    private final String MASK_PASSWORD = "******";

    @Autowired
    private UserRepository userRepository;

    @Override
    public int registerUsers(User user) {
        User temp = userRepository.findByEmail(user.getEmail());
        if (temp != null) throw new InputValidationExceptionHandler("User is already exists!");
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(Helper.getHashPassword(user.getPassword()));
        newUser.setAddress(user.getAddress());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setGender(user.getGender());
        newUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(newUser);
        return 201;
    }

    @Override
    public User login(User user) {
        User temp = userRepository.findByUsername(user.getUsername());
        if (temp == null) throw new DataValidationExceptionHandler("User not found!");
        String password = Helper.getHashPassword(user.getPassword());
        if (!temp.getPassword().equalsIgnoreCase(password))throw new InputValidationExceptionHandler("Incorrect password!");
        if(temp.isLogin())throw new DataValidationExceptionHandler("User already login!");
        temp.setLogin(true);
        this.userRepository.save(temp);
        temp.setPassword(MASK_PASSWORD);
        return temp;
    }

    @Override
    public int resetPassword(User user) {
        User temp = userRepository.findByEmail(user.getEmail());
        if (temp == null) throw new DataValidationExceptionHandler("User not found!");
        String password = Helper.getHashPassword(user.getPassword());
        if(temp.getPassword().equalsIgnoreCase(password)) throw new InputValidationExceptionHandler("Your new password have to be different from the old password");
        temp.setPassword(password);
        userRepository.save(temp);
        return 201;
    }

    @Override
    public User editUser(Long id,User user) {
        if(!userRepository.findById(id).isPresent())throw new DataValidationExceptionHandler("User not found!");
        User temp = userRepository.findById(id).get();
        if(!temp.isLogin())throw new InputValidationExceptionHandler("You're not authorized!");
        temp.setEmail(user.getEmail());
        temp.setGender(user.getGender());
        temp.setFirstName(user.getFirstName());
        temp.setLastName(user.getLastName());
        temp.setAddress(user.getAddress());
        temp.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(temp);
        temp.setPassword(MASK_PASSWORD);
        return temp;
    }

    @Override
    public int logout(Long id) {
        if(!userRepository.findById(id).isPresent())throw new DataValidationExceptionHandler("User not found!");
        User temp = userRepository.findById(id).get();
        if(!temp.isLogin())throw new InputValidationExceptionHandler("You're not authorized!");
        temp.setLogin(false);
        userRepository.save(temp);
        return 200;
    }

    @Override
    public User showProfile(Long id) {
        if(!userRepository.findById(id).isPresent())throw new DataValidationExceptionHandler("User not found!");
        User temp = userRepository.findById(id).get();
        if(!temp.isLogin())throw new InputValidationExceptionHandler("You're not authorized!");
        temp.setPassword(MASK_PASSWORD);
        return temp;
    }


}
