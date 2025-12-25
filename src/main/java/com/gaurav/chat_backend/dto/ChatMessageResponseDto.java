package com.gaurav.chat_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageResponseDto {
    private String message;
    private String from;
}
