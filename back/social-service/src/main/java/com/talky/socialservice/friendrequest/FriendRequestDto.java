package com.talky.socialservice.friendrequest;

import com.talky.commons.users.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
class FriendRequestDto {
  private UUID id;

  private UserDto sender;
  private UserDto recipient;

  private FriendRequestStatus status;

  private LocalDateTime creationDate;
  private LocalDateTime lastUpdate;
}
