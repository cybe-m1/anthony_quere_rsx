package com.talky.socialservice.friends;

import com.talky.commons.users.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class FriendDto extends UserDto {
  private UUID friendshipId;

  public FriendDto(UUID friendshipId, UserDto user) {
    super(user.getId(), user.getDisplayedName(), user.getProfilePicture(), user.getLastSeen());
    this.friendshipId = friendshipId;
  }
}
