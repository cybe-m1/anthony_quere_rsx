package com.talky.postservice.post;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/posts")
class PostControllerV2 {

  private final PostService postService;

  @Operation(description = "List public posts")
  @GetMapping
  Page<PostDto> listPosts(@ParameterObject Pageable pageable, @RequestParam("searchStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime searchStart) {
    return postService.pagePosts(searchStart, pageable);
  }

}
