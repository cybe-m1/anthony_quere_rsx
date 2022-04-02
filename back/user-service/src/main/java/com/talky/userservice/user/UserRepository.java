package com.talky.userservice.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByAccountId(String accountId);
}
