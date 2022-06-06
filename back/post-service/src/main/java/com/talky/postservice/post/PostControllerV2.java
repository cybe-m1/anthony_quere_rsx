package com.talky.postservice.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/posts")
class PostControllerV2 {

  private final PostService postService;

  @Operation(description = "List public posts with pagination")
  @GetMapping
  Page<PostDto> listPosts(@ParameterObject Pageable pageable, @RequestParam("searchStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime searchStart) {
    return postService.pagePosts(searchStart, pageable);
  }

  @Operation(description = "List posts from a user with pagination")
  @GetMapping("/author/{authorId}")
  Page<PostDto> listUserPosts(@Parameter(description = "UUID of the posts author") @PathVariable UUID authorId, @ParameterObject Pageable pageable,  @RequestParam("searchStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime searchStart) {
    return postService.pageUserPosts(authorId, searchStart, pageable);
  }

}
