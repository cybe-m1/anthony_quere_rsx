package com.talky.postservice.post;

import com.talky.commons.users.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
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
  @Schema(description = "Uniq identifier of the post")
  private UUID id;
  @Schema(description = "Informations about the post author")
  private UserDto author;
  @Schema(description = "Post creation date")
  private LocalDateTime createdAt;
  @Schema(description = "Post text content")
  private String content;
  @Schema(description = "Post privacy (public / private)")
  private PostPrivacy privacy;
  @Schema(description = "List of assets links, these are temporary link, only available during a certain period of time.")
  private List<String> assets;
}
