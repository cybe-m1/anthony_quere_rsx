package com.talky.userservice.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
  private UUID id;

  private String username;

  private String displayedName;

  private String profilePicture;
}
