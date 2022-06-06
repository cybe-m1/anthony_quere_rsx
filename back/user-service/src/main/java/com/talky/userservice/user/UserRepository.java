package com.talky.userservice.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByAccountId(String accountId);

  Page<User> findByIdNotAndDisplayedNameContaining(UUID userId, String search, Pageable pageable);
}
