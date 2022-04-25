package com.talky.postservice.post;

import com.talky.commons.assets.IAssets;
import com.talky.commons.users.IUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class PostService implements IPost {
  private final PostRepository repository;
  private final IUsers users;
  private final PostMapper mapper;
  private final IAssets assets;

  PostDto toDto(Post post) {
    var dto = mapper.toDto(post);
    dto.setAuthor(users.getUserById(post.getAuthor()));
    dto.setAssets(post.getAssets().stream().map(assetId -> assets.getTemporaryLink("posts-dev", assetId).getUrl().toString()).toList());
    return dto;
  }

  PostDto createPost(CreatePostRequestDto dto) {
    var currentUser = users.getCurentUser();
    var post = new Post(currentUser.getId(), dto.getContent(), dto.getPrivacy(), dto.getAssets());

    return mapper.toDto(repository.save(post));
  }

  List<PostDto> listPosts(Pageable pageable) {
    return repository.findPostsByPrivacy(PostPrivacy.PUBLIC, pageable)
      .stream()
      .map(this::toDto)
      .toList();
  }

  List<PostDto> getUserPosts(UUID userId, Pageable pageable) {
    var currentUser = users.getCurentUser();

    if (userId.equals(currentUser.getId())) {
      return getUserAllPosts(userId, pageable);
    }
    return getUserPublicPosts(userId, pageable);
  }

  List<PostDto> getUserAllPosts(UUID userId, Pageable pageable) {
    return repository.findPostsByAuthorOrderByCreatedAtDesc(userId, pageable)
      .stream()
      .map(this::toDto)
      .toList();
  }

  List<PostDto> getUserPublicPosts(UUID userId, Pageable pageable) {
    return repository.findPostsByAuthorAndPrivacyOrderByCreatedAtDesc(userId, PostPrivacy.PUBLIC, pageable)
      .stream()
      .map(this::toDto)
      .toList();
  }
}
