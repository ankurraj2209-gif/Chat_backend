package com.gaurav.chat_backend.service;


import com.gaurav.chat_backend.entity.User;
import com.gaurav.chat_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUser {
    private  UserRepository userRepository;
    public AuthenticateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean authenticate(Long userid ) {
        User user = userRepository.findUserByid(userid);
        if (user == null) {
            return false;
        }
        return true;
    }
}
