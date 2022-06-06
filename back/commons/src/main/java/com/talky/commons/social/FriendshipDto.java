package com.talky.commons.social;

import com.talky.commons.users.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
public class FriendshipDto {
  @Schema(description = "UUID of the friendship")
  private UUID id;

  @Schema(description = "List of friends in the friendship")
  private Collection<UserDto> friends;

  @Schema(description = "Creation datetime")
  private LocalDateTime creationDate;

  public boolean isPartOfFriendship(UUID userID) {
    return friends.stream().anyMatch(dto -> dto.getId().equals(userID));
  }
}
