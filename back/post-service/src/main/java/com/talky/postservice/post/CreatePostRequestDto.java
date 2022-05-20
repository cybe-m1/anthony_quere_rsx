package com.talky.postservice.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CreatePostRequestDto {
  @Schema(description = "Text content", example = "Gingerbread I love sesame snaps topping topping gummies dragée I love pudding. Dessert wafer cupcake soufflé marshmallow lemon drops pastry.")
  private String content;
  @Schema(description = "Privacy of the post (PUBLIC: Any user can see it, PRIVATE: Only author and friends can see it)")
  private PostPrivacy privacy;
  @Schema(description = "List of assets ids (remember to add the extention)")
  private List<String> assets;
}
