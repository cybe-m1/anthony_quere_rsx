package com.talky.postservice.post;

import com.talky.commons.assets.IAssets;
import com.talky.commons.assets.dto.AssetTemporaryLinkResponseDto;
import com.talky.commons.exceptions.TalkyUnauthorizedException;
import com.talky.commons.users.IUsers;
import com.talky.commons.users.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class PostService implements IPost {
  private final PostRepository repository;
  private final IUsers users;
  private final PostMapper mapper;
  private final IAssets assets;

  private final static String ASSET_BUCKET_DIR = "posts-assets";

  AssetTemporaryLinkResponseDto getAssetUploadLink(String extension) {
    return assets.getUploadTemporaryLink(ASSET_BUCKET_DIR, extension);
  }

  PostDto toDto(Post post) {
    var dto = mapper.toDto(post);
    dto.setAuthor(users.getUserById(post.getAuthor()));
    dto.setAssets(post.getAssets().stream().map(assetId -> assets.getTemporaryLink(ASSET_BUCKET_DIR, assetId).getUrl().toString()).toList());
    return dto;
  }

  PostDto createPost(CreatePostRequestDto dto) {
    var currentUser = users.getCurentUser();
    var post = new Post(currentUser.getId(), dto.getContent(), dto.getPrivacy(), dto.getAssets());

    return toDto(repository.save(post));
  }

  List<PostDto> listPosts(Pageable pageable) {
    return repository.findPostsByPrivacy(PostPrivacy.PUBLIC, pageable)
      .stream()
      .map(this::toDto)
      .toList();
  }

  Page<PostDto> pagePosts(LocalDateTime searchStartDateTime, Pageable pageable) {
    return repository.findPostsByPrivacyAndCreatedAtBeforeOrderByCreatedAtDesc(PostPrivacy.PUBLIC, searchStartDateTime, pageable).map(this::toDto);
  }

  Page<PostDto> pageUserPosts(UUID userId, LocalDateTime searchStartDateTime, Pageable pageable) {
    UserDto currentUser;
    try {
      currentUser = users.getCurentUser();
      if (userId.equals(currentUser.getId())) {
        return repository.findPostsByCreatedAtBeforeAndAuthorOrderByCreatedAtDesc(searchStartDateTime, userId, pageable).map(this::toDto);
      }
    } catch (Exception ignored) { }
    return repository.findPostsByPrivacyAndCreatedAtBeforeAndAuthorOrderByCreatedAtDesc(PostPrivacy.PUBLIC, searchStartDateTime, userId, pageable).map(this::toDto);
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
