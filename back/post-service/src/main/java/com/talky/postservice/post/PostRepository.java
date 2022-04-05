package com.talky.postservice.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;

interface PostRepository extends JpaRepository<Post, UUID> {
  List<Post> findPostsByAuthorOrderByCreatedAtDesc(UUID userId, Pageable pageable);
  List<Post> findPostsByAuthorAndPrivacyOrderByCreatedAtDesc(UUID userId, PostPrivacy privacy, Pageable pageable);

  List<Post> findPostsByPrivacy(PostPrivacy privacy, Pageable pageable);
}
