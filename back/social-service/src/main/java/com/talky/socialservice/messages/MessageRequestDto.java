package com.talky.socialservice.messages;

import lombok.Data;

import java.util.UUID;

@Data
class MessageRequestDto {
  private UUID friendshipId;
  private String content;
}
