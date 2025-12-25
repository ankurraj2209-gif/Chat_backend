package com.gaurav.chat_backend.service;

import com.gaurav.chat_backend.entity.User;
import com.gaurav.chat_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    @Autowired
    private UserRepository userRepository;
    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(String username) {
        User user = new User();
        user.setUsername(username);
        return userRepository.save(user);
    }
}
