package com.talky.userservice.user;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDto {
  private UUID id;

  private String displayedName;

  private String profilePicture;

  private LocalDateTime lastSeen;
}
