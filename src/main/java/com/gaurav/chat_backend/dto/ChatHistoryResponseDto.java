package com.gaurav.chat_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatHistoryResponseDto {
    private String senderUsername;
    private String receiverUsername;
    private String message;
    private LocalDateTime timestamp;
}
