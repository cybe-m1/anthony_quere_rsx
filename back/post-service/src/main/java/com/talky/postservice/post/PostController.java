package com.talky.postservice.post;

import com.talky.commons.assets.dto.AssetTemporaryLinkResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
class PostController {
  private final PostService postService;

  @Operation(description = "List posts from a user")
  @GetMapping("/author/{authorId}")
  List<PostDto> listUserPosts(@Parameter(description = "UUID of the posts author") @PathVariable UUID authorId, @ParameterObject Pageable pageable) {
    return postService.getUserPosts(authorId, pageable);
  }

  @Operation(description = "List public posts")
  @GetMapping
  List<PostDto> listPosts(@ParameterObject Pageable pageable) {
    return postService.listPosts(pageable);
  }

  @Operation(description = "Create a post")
  @PostMapping
  PostDto createPost(@RequestBody CreatePostRequestDto dto) {
    return postService.createPost(dto);
  }

  @Operation(description = "Generate an upload link for an asset. The link can be used by a form to upload an asset. The generated link is available for 30 minutes.")
  @GetMapping("/asset/upload")
  AssetTemporaryLinkResponseDto getAssetUploadLink(@RequestParam String extension) {
    return postService.getAssetUploadLink(extension);
  }
}
