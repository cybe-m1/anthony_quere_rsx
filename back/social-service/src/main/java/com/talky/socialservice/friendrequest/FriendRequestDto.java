package com.talky.socialservice.friendrequest;

import com.talky.commons.users.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
class FriendRequestDto {
  @Schema(description = "UUID of the friendship")
  private UUID id;

  @Schema(description = "Details of the user that created the friend request")
  private UserDto sender;
  @Schema(description = "Details of the user that recieved the friend request")
  private UserDto recipient;

  @Schema(description = "Current status of the friend request")
  private FriendRequestStatus status;

  @Schema(description = "Creation datetime")
  private LocalDateTime creationDate;
  @Schema(description = "Last update datetime")
  private LocalDateTime lastUpdate;
}
