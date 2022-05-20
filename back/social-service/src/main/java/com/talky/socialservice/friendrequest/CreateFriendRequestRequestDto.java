package com.talky.socialservice.friendrequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
class CreateFriendRequestRequestDto {
  @Schema(description = "UUID of the user that will recieve the friend request")
  private UUID recipient;
}
