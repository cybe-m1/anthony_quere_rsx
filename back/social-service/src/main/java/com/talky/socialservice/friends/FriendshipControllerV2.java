package com.talky.socialservice.friends;

import com.talky.commons.social.FriendshipDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/friends")
class FriendshipControllerV2 {
  private final FriendshipService friendshipService;

  @Operation(description = "List all friends of the current user with pagination")
  @GetMapping
  Page<FriendDto> listFriendsWithPagination(@ParameterObject Pageable pageable) {
    return friendshipService.getFriendsWithPagination(pageable);
  }
}
