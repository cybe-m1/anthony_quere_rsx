package com.talky.socialservice.friends;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {
  Page<Friendship> findByFriendAOrFriendB(UUID friendA, UUID friendB, Pageable pageable);

  @Query("select f from Friendship f WHERE (f.friendA = :friendA AND f.friendB = :friendB) OR (f.friendA = :friendB AND f.friendB = :friendA)")
  Optional<Friendship> findByFriends(UUID friendA, UUID friendB);
}
