package com.talky.socialservice.friends;

import com.talky.commons.users.UserDto;

import java.util.UUID;

public interface IFriendship {
  FriendshipDto createFriendship(UserDto friendA, UserDto friendB);

  FriendshipDto getFriendship(UUID friendship);
}
