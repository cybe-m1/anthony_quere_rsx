package com.talky.socialservice.friendrequest;

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
@Table(name = "t_friendrequest")
class FriendRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private UUID sender;
  private UUID recipient;

  @Enumerated(EnumType.STRING)
  private FriendRequestStatus status;

  private LocalDateTime creationDate;
  private LocalDateTime lastUpdate;

  @PreUpdate
  public void preUpdate() {
    lastUpdate = LocalDateTime.now();
  }

  public FriendRequest(UUID sender, UUID recipient) {
    this.sender = sender;
    this.recipient = recipient;

    this.status = FriendRequestStatus.PENDING;
    this.lastUpdate = LocalDateTime.now();
    this.creationDate = LocalDateTime.now();
    this.id = UUID.randomUUID();
  }
}
