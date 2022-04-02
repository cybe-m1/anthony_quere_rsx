package com.talky.userservice.user;

import lombok.Data;

@Data
public class CreateUserRequestDto {
  private String displayedName;
  private String profilePicture = "";
  private String defaultProfilePicture;
}
