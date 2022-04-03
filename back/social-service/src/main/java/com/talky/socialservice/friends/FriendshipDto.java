package com.talky.socialservice.friends;

import com.talky.commons.users.UserDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FriendshipDto {
  private UUID id;

  private UserDto friendA;
  private UserDto friendB;

  private LocalDateTime creationDate;
}
