package com.application.Issue.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Issue.Model.User;
import com.application.Issue.Repository.UserRepository;
import com.application.Issue.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    public UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void removeUser(String userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    public User findUserByName(User user) {
        String username = user.getUserName();
        return userRepository.findByUserName(username);
    }

    public User findUser(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUserPassword(User user, String userPassword) {
        user.setUserPassword(userPassword);
        return userRepository.save(user);
    }

    public User updateLocation(User user, String location) {
         user.setLocation(location);
        return userRepository.save(user);
    }
}
