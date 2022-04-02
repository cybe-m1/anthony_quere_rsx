package com.talky.userservice.user;

import lombok.Data;

@Data
class UpdateUserRequestDto {
  private String displayedName;
  private String profilePicture = "";
}
