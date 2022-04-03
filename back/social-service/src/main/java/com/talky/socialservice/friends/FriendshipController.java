package com.talky.socialservice.friends;

import lombok.RequiredArgsConstructor;
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

  @GetMapping
  List<FriendDto> listFriends() {
    return friendshipService.getFriends(Pageable.ofSize(Integer.MAX_VALUE));
  }
}
