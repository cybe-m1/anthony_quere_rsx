package com.talky.socialservice.friendrequest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/requests")
@RequiredArgsConstructor
class FriendRequestController {
  private final FriendRequestService service;

  @GetMapping
  Set<FriendRequestDto> listFriendRequests() {
    return service.getRecievedFriendRequests();
  }

  @PostMapping
  FriendRequestDto createFriendRequest(@RequestBody CreateFriendRequestRequestDto dto) {
    return service.createFriendRequest(dto.getRecipient());
  }

  @PutMapping("/{requestId}")
  void updateFriendRequest(@RequestBody UpdateFriendRequestRequestDto dto, @PathVariable UUID requestId) {
    if (dto.getStatus().equals(FriendRequestStatus.ACCEPTED)) {
      service.acceptFriendRequest(requestId);
    } else if (dto.getStatus().equals(FriendRequestStatus.DENIED)) {
      service.refuseFriendRequest(requestId);
    }
  }


}
