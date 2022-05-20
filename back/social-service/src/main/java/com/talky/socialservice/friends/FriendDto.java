package com.talky.socialservice.friends;

import com.talky.commons.users.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class FriendDto extends UserDto {
  @Schema(description = "UUID of the friendship between the current user and this user")
  private UUID friendshipId;

  public FriendDto(UUID friendshipId, UserDto user) {
    super(user.getId(), user.getDisplayedName(), user.getProfilePicture(), user.getLastSeen());
    this.friendshipId = friendshipId;
  }
}
