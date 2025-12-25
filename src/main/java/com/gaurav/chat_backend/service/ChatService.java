package com.gaurav.chat_backend.service;

import com.gaurav.chat_backend.dto.ChatMessageRequestDto;
import com.gaurav.chat_backend.dto.ChatMessageResponseDto;
import com.gaurav.chat_backend.entity.ChatMessage;
import com.gaurav.chat_backend.entity.User;
import com.gaurav.chat_backend.repository.ChatMessageRepository;
import com.gaurav.chat_backend.repository.UserRepository;
import org.springframework.messaging.core.AbstractMessageReceivingTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class ChatService {
    private UserRepository userRepository;
    private ChatMessageRepository chatMessageRepository;
    private SimpMessagingTemplate messagingTemplate;

    public ChatService(UserRepository userRepository, ChatMessageRepository chatMessageRepository) {
        this.userRepository = userRepository;
        this.chatMessageRepository = chatMessageRepository;
    }
    public void sendPrivateMessage(ChatMessageRequestDto request, Principal principal) {
        // WebSocket identity (String)
         String senderStr = principal.getName();
         String receiverStr = request.getTo();
         Long senderId = Long.valueOf(senderStr);
         Long receiverId = Long.valueOf(receiverStr);
         User senderUser=userRepository.findUserByid(senderId);
         User receiverUser=userRepository.findUserByid(receiverId);

          ChatMessage entity = new ChatMessage();
          entity.setSender(senderUser);
          entity.setReceiver(receiverUser);
          entity.setMessage(request.getMessage());
          entity.setTimestamp(LocalDateTime.now());
          chatMessageRepository.save(entity);

            ChatMessageResponseDto responseDto = new ChatMessageResponseDto();
            responseDto.setMessage(request.getMessage());
            responseDto.setFrom(senderUser.getUsername());
        messagingTemplate.convertAndSendToUser( receiverStr,
                "/queue/messages",
                responseDto );

    }
}
