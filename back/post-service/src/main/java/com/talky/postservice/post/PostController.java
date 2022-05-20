package com.talky.postservice.post;

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
}
