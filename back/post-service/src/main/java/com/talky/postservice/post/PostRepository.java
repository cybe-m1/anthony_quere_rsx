package com.talky.postservice.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

interface PostRepository extends JpaRepository<Post, UUID> {
  List<Post> findPostsByAuthorOrderByCreatedAtDesc(UUID userId, Pageable pageable);
  List<Post> findPostsByAuthorAndPrivacyOrderByCreatedAtDesc(UUID userId, PostPrivacy privacy, Pageable pageable);

  List<Post> findPostsByPrivacy(PostPrivacy privacy, Pageable pageable);

  Page<Post> findPostsByPrivacyAndCreatedAtBeforeOrderByCreatedAtDesc(PostPrivacy privacy, LocalDateTime datetime, Pageable pageable);
  Page<Post> findPostsByPrivacyAndCreatedAtBeforeAndAuthorOrderByCreatedAtDesc(PostPrivacy privacy, LocalDateTime datetime, UUID author, Pageable pageable);
  Page<Post> findPostsByCreatedAtBeforeAndAuthorOrderByCreatedAtDesc(LocalDateTime datetime, UUID author, Pageable pageable);
}
