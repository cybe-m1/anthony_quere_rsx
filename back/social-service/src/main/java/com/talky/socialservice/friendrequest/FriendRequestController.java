package com.talky.socialservice.friendrequest;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/requests")
@RequiredArgsConstructor
class FriendRequestController {
  private final FriendRequestService service;

  @Operation(description = "List friend requests of the current user")
  @GetMapping
  Set<FriendRequestDto> listFriendRequests() {
    return service.getRecievedFriendRequests();
  }

  @Operation(description = "Create a friend request")
  @PostMapping
  FriendRequestDto createFriendRequest(@RequestBody CreateFriendRequestRequestDto dto) {
    return service.createFriendRequest(dto.getRecipient());
  }

  @Operation(description = "Change the status of a friend request (ACCEPTED / DENIED)")
  @PutMapping("/{requestId}")
  void updateFriendRequest(@RequestBody UpdateFriendRequestRequestDto dto, @PathVariable UUID requestId) {
    if (dto.getStatus().equals(FriendRequestStatus.ACCEPTED)) {
      service.acceptFriendRequest(requestId);
    } else if (dto.getStatus().equals(FriendRequestStatus.DENIED)) {
      service.refuseFriendRequest(requestId);
    }
  }
}
