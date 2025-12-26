package com.gaurav.chat_backend.controller;

import com.gaurav.chat_backend.service.UserStatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    public class UserStatusController {

        private UserStatusService tracker;

        public UserStatusController(UserStatusService tracker) {
            this.tracker = tracker;
        }

        @GetMapping("/users/{userId}/status")
        public String getStatus(@PathVariable String userId) {
            return tracker.isOnline(userId) ? "ONLINE" : "OFFLINE";
        }
    }

