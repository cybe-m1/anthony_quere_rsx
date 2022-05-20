package com.talky.socialservice.friends;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends")
class FriendshipController {
  private final FriendshipService friendshipService;

  @Operation(description = "List all friends of the current user")
  @GetMapping
  List<FriendDto> listFriends(@ParameterObject Pageable pageable) {
    return friendshipService.getFriends(pageable);
  }
}
