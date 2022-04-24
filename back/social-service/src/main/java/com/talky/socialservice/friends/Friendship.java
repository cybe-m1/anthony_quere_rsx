package com.talky.socialservice.friends;

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
@Table(name = "t_friendship")
class Friendship {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "friend_a")
  private UUID friendA;
  @Column(name = "friend_b")
  private UUID friendB;

  private LocalDateTime creationDate;

  public Friendship(UUID friendA, UUID friendB) {
    this.friendA = friendA;
    this.friendB = friendB;

    this.creationDate = LocalDateTime.now();
    this.id = UUID.randomUUID();
  }

}
