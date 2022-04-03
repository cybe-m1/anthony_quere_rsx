package com.talky.commons.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  protected UUID id;

  protected String displayedName;

  protected String profilePicture;

  protected LocalDateTime lastSeen;
}
