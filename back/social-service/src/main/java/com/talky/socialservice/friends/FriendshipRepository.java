package com.talky.socialservice.friends;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {
  List<Friendship> findByFriendAOrFriendB(UUID friendA, UUID friendB, Pageable pageable);
}
