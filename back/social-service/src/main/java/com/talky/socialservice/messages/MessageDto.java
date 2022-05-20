package com.talky.socialservice.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talky.socialservice.pushnotification.MessageData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
public class MessageDto implements MessageData {
  @Schema(description = "Uniq UUID of the message")
  private UUID id;
  @Schema(description = "UUID of the friendship where the message was sended")
  private UUID friendshipId;
  @Schema(description = "UUID of the user that made the request")
  private UUID author;
  @Schema(description = "Text content of the message")
  private String content;
  @Schema(description = "Creation date of the message")
  private LocalDateTime createdAt;

  @Override
  public Map<String, String> toMessageData() {
    return Map.of(
      "id", id.toString(),
      "friendshipId", friendshipId.toString(),
      "author",  author.toString(),
      "content", content,
      "createdAt", createdAt.toString()
    );
  }
}
