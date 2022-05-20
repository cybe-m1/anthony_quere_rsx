package com.talky.socialservice.messages;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
class MessageRequestDto {
  @Schema(description = "UUID of the friendship where the message should be sent")
  private UUID friendshipId;
  @Schema(description = "Text content of the message")
  private String content;
}
