package com.talky.socialservice.friends;

import com.talky.commons.users.UserDto;

public interface IFriendship {
  FriendshipDto createFriendship(UserDto friendA, UserDto friendB);
}
