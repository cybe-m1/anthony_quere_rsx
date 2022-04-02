package com.talky.userservice.user;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_user")
class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotNull
  @Column(unique=true)
  private String accountId;

  @NotNull
  private String displayedName;

  private String profilePicture;

  @NotNull
  private String defaultProfilePicture;

  private LocalDateTime lastSeen = null;





}
