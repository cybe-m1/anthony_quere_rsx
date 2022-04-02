package com.talky.userservice.user;

import lombok.Data;

@Data
public class UpdateUserRequestDto {
  private String displayedName;
  private String profilePicture = "";
}
