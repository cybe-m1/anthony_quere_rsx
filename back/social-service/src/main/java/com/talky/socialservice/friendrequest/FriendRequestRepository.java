package com.talky.socialservice.friendrequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, UUID> {

  Set<FriendRequest> findBySender(UUID sender);

  Set<FriendRequest> findByRecipientAndStatus(UUID recipient, FriendRequestStatus status);

  Optional<FriendRequest> findByRecipientAndIdAndStatus(UUID recipient, UUID id, FriendRequestStatus status);

  @Query("SELECT fr from FriendRequest fr where " +
    "((fr.sender = :user1 and fr.recipient = :user2) or" +
    "(fr.sender = :user2 and fr.recipient = :user1)) and fr.status = 'PENDING'")
  Optional<FriendRequest> findFriendRequestByUsers(@Param("user1") UUID user1, @Param("user2") UUID user2);

}
