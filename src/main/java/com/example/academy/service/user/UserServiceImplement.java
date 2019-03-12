package com.example.academy.service.user;

import com.example.academy.helper.Helper;
import com.example.academy.helper.Message;
import com.example.academy.model.custom.exception.DataValidationExceptionHandler;
import com.example.academy.model.custom.exception.InputValidationExceptionHandler;
import com.example.academy.model.entity.User;
import com.example.academy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserServiceImplement")
public class UserServiceImplement implements UserService {
    private static final String MASK_IDENTITY = "******";

    @Autowired
    private UserRepository userRepository;

    @Override
    public int registerUsers(User user) {
        User temp = userRepository.findByEmail(user.getEmail());
        if (temp != null) throw new InputValidationExceptionHandler(Message.MSG_EXST_USER);
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(Helper.getHashPassword(user.getPassword()));
        newUser.setAddress(user.getAddress());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setGender(user.getGender());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setLogin(false);
        userRepository.save(newUser);
        return 201;
    }

    @Override
    public User login(User user) {
        User temp = userRepository.findByUsername(user.getUsername());
        if (temp == null) throw new DataValidationExceptionHandler(Message.MSG_NO_USER);
        String password = Helper.getHashPassword(user.getPassword());
        if (!temp.getPassword().equalsIgnoreCase(password))throw new InputValidationExceptionHandler(Message.MSG_INCORRECT_IDENTITY);
        if(temp.isLogin())throw new DataValidationExceptionHandler(Message.MSG_LGN_USER);
        temp.setLogin(true);
        this.userRepository.save(temp);
        temp.setPassword(MASK_IDENTITY);
        return temp;
    }

    @Override
    public int resetPassword(User user) {
        User temp = userRepository.findByEmail(user.getEmail());
        if (temp == null) throw new DataValidationExceptionHandler(Message.MSG_NO_USER);
        String password = Helper.getHashPassword(user.getPassword());
        if(temp.getPassword().equalsIgnoreCase(password)) throw new InputValidationExceptionHandler(Message.MSG_DIFF_IDENTITY);
        temp.setPassword(password);
        userRepository.save(temp);
        return 201;
    }

    @Override
    public User editUser(Long id,User user) {
        User dataUser = userRepository.findById(id).orElse(null);
        if(dataUser == null)throw new DataValidationExceptionHandler(Message.MSG_NO_USER);
        if(!dataUser.isLogin())throw new InputValidationExceptionHandler(Message.MSG_NOT_AUTH_USER);
        dataUser.setEmail(user.getEmail());
        dataUser.setGender(user.getGender());
        dataUser.setFirstName(user.getFirstName());
        dataUser.setLastName(user.getLastName());
        dataUser.setAddress(user.getAddress());
        dataUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(dataUser);
        dataUser.setPassword(MASK_IDENTITY);
        return dataUser;
    }

    @Override
    public int logout(Long id) {
        User dataUser = userRepository.findById(id).orElse(null);
        if(dataUser == null)throw new DataValidationExceptionHandler(Message.MSG_NO_USER);
        if(!dataUser.isLogin())throw new InputValidationExceptionHandler(Message.MSG_NOT_AUTH_USER);
        dataUser.setLogin(false);
        userRepository.save(dataUser);
        return 200;
    }

    @Override
    public User showProfile(Long id) {
        User dataUser = userRepository.findById(id).orElse(null);
        if(dataUser == null)throw new DataValidationExceptionHandler(Message.MSG_NO_USER);
        if(!dataUser.isLogin())throw new InputValidationExceptionHandler(Message.MSG_NOT_AUTH_USER);
        dataUser.setPassword(MASK_IDENTITY);
        return dataUser;
    }


}
