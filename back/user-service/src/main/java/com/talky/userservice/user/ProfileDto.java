package com.talky.userservice.user;

import com.talky.commons.users.UserDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto extends UserDto {
  private boolean areFriends;
}
