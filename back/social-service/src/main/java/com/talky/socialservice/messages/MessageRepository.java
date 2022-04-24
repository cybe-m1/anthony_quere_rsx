package com.talky.socialservice.messages;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;


interface MessageRepository extends JpaRepository<Message, UUID> {
  Page<Message> getByFriendshipIdAndCreatedAtBefore(UUID friendshipId, LocalDateTime localDateTime, Pageable pageable);
  Page<Message> getByFriendshipIdAndCreatedAtAfter(UUID friendshipId, LocalDateTime localDateTime, Pageable pageable);
}
