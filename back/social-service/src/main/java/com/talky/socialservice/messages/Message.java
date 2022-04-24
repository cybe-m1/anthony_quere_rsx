package com.talky.socialservice.messages;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "t_message")
class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private UUID friendshipId;
  private UUID author;

  private String content;

  private LocalDateTime createdAt;
}
