package com.talky.commons.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  @Schema(description = "Uniq UUID of the User")
  protected UUID id;

  @Schema(description = "Name that the use chose to display")
  protected String displayedName;

  @Schema(description = "Link to the user profile picture")
  protected String profilePicture;

  @Schema(description = "Date time of when the user was last online")
  protected LocalDateTime lastSeen;
}
