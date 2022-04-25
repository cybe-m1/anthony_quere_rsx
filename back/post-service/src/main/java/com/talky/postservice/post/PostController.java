package com.talky.postservice.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
class PostController {
  private final PostService postService;

  @GetMapping("/author/{authorId}")
  List<PostDto> listUserPosts(@PathVariable UUID authorId) {
    return postService.getUserPosts(authorId, Pageable.ofSize(Integer.MAX_VALUE));
  }

  @GetMapping
  List<PostDto> listPosts() {
    return postService.listPosts(Pageable.ofSize(Integer.MAX_VALUE));
  }

  @PostMapping
  PostDto createPost(@RequestBody CreatePostRequestDto dto) {
    return postService.createPost(dto);
  }
}
