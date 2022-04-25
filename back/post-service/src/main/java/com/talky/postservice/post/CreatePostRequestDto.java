package com.talky.postservice.post;

import lombok.Data;

import java.util.List;

@Data
public class CreatePostRequestDto {
  private String content;
  private PostPrivacy privacy;
  private List<String> assets;
}
