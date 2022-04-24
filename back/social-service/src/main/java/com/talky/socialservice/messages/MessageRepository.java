package com.talky.socialservice.messages;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


interface MessageRepository extends JpaRepository<Message, UUID> {
  List<Message> getByFriendshipIdAndCreatedAtBefore(UUID friendshipId, LocalDateTime localDateTime, Pageable pageable);
  List<Message> getByFriendshipIdAndCreatedAtAfter(UUID friendshipId, LocalDateTime localDateTime, Pageable pageable);
}
