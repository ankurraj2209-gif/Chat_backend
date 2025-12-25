package com.gaurav.chat_backend.controller;

import com.gaurav.chat_backend.dto.ChatHistoryResponseDto;
import com.gaurav.chat_backend.service.ChatHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatHistoryController {

        private final ChatHistoryService chatHistoryService;
        public ChatHistoryController(ChatHistoryService chatHistoryService) {
            this.chatHistoryService = chatHistoryService;
        }

        @GetMapping("/history")
        public ResponseEntity<?> getChatHistory(
                @RequestParam Long user1,
                @RequestParam Long user2) {

            try{
                List<ChatHistoryResponseDto> history = chatHistoryService
                        .getChatHistory(user1, user2);
                return ResponseEntity.ok(history);

            }
            catch(Exception e){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(e.getMessage());

            }
        }
    }



