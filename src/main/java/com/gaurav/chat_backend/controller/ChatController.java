package com.gaurav.chat_backend.controller;
import com.gaurav.chat_backend.dto.ChatMessageRequestDto;
import com.gaurav.chat_backend.dto.ChatMessageResponseDto;
import com.gaurav.chat_backend.entity.ChatMessage;
import com.gaurav.chat_backend.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;


@Controller
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.send")
    public void send(ChatMessageRequestDto request, Principal principal) {
        chatService.sendPrivateMessage(request,principal);
    }
}
