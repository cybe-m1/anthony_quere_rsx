package com.talky.socialservice.friendrequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
class UpdateFriendRequestRequestDto {
  @Schema(description = "New status of the request (ACCEPTED / DENIED / PENDING)")
  private FriendRequestStatus status;
}
