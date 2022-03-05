package com.talky.userservice.user;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CreateUserRequestDto {
  @NotNull
  private String username;
  private String displayedName;
  private String profilePicture = "";
}
