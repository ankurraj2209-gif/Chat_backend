package com.gaurav.chat_backend.service;

import com.gaurav.chat_backend.dto.ChatHistoryResponseDto;
import com.gaurav.chat_backend.entity.ChatMessage;
import com.gaurav.chat_backend.entity.User;
import com.gaurav.chat_backend.exception.UserNotFoundException;
import com.gaurav.chat_backend.repository.ChatMessageRepository;
import com.gaurav.chat_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatHistoryService {
    private ChatMessageRepository chatMessageRepository;
    private UserRepository userRepository;

    public ChatHistoryService(ChatMessageRepository chatMessageRepository, UserRepository userRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
    }

    public List<ChatHistoryResponseDto> getChatHistory(Long user1Id, Long user2Id) {

        User user1 = userRepository.findUserByid(user1Id);
        if (user1 == null) {
            throw new UserNotFoundException("User1 not found");
        }

        User user2 = userRepository.findUserByid(user2Id);
        if (user2 == null) {
            throw new UserNotFoundException("User2 not found");
        }

        List<ChatMessage> messages =
                chatMessageRepository
                        .findBySenderAndReceiverOrSenderAndReceiverOrderByTimestampAsc(
                                user1, user2,
                                user2, user1
                        );
        List<ChatHistoryResponseDto> response = new ArrayList<>();
        for (ChatMessage msg : messages) {
            ChatHistoryResponseDto dto = new ChatHistoryResponseDto();
                   dto.setSenderUsername(msg.getSender().getUsername());
                   dto.setReceiverUsername(msg.getReceiver().getUsername());
                   dto.setMessage(msg.getMessage());
                    dto.setTimestamp(msg.getTimestamp());

            response.add(dto);
        }

        return response ;
    }
}
