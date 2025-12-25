package com.gaurav.chat_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequestDto {
  private String to;
  private String message;
}
