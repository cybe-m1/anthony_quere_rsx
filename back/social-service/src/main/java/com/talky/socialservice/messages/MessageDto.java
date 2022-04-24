package com.talky.socialservice.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talky.socialservice.pushnotification.MessageData;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
public class MessageDto implements MessageData {
  private UUID id;
  private UUID friendshipId;
  private UUID author;
  private String content;
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
