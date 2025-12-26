package com.gaurav.chat_backend.controller;

import com.gaurav.chat_backend.entity.User;
import com.gaurav.chat_backend.service.CreateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

public class CreateUserController {
    private CreateUserService createUserService;
    public CreateUserController(CreateUserService createUserService) {
       this.createUserService = createUserService;
    }
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody String username) {
        createUserService.createUser(username);
        return ResponseEntity.ok().body("User created");
    }

}
