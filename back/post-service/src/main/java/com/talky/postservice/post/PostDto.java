package com.talky.postservice.post;

import com.talky.commons.users.UserDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
class PostDto {
  private UUID id;
  private UserDto author;
  private LocalDateTime createdAt;
  private String content;
  private PostPrivacy privacy;
  private List<String> assets;
}
