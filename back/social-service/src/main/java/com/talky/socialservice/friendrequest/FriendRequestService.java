package com.talky.socialservice.friendrequest;

import com.talky.commons.users.IUsers;
import com.talky.commons.users.UserDto;
import com.talky.socialservice.friends.IFriendship;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class FriendRequestService implements IFriendRequest {
  private final IUsers users;
  private final FriendRequestRepository repository;
  private final FriendRequestMapper mapper;
  private final IFriendship iFriendship;

  private FriendRequestDto toDto(FriendRequest fr) {
    var dto = mapper.entityToDto(fr);
    dto.setRecipient(users.getUserById(fr.getRecipient()));
    dto.setSender(users.getUserById(fr.getSender()));
    return dto;
  }

  private FriendRequestDto toDto(FriendRequest fr,UserDto recipient) {
    var dto = mapper.entityToDto(fr);
    dto.setRecipient(recipient);
    dto.setSender(users.getUserById(fr.getRecipient()));
    return dto;
  }


  private FriendRequestDto toDto(FriendRequest fr, UserDto sender, UserDto recipient) {
    var dto = mapper.entityToDto(fr);
    dto.setRecipient(recipient);
    dto.setSender(sender);
    return dto;
  }

  public FriendRequestDto createFriendRequest(UUID recipientId) {
    // Check if user exists
    UserDto recipient, sender;
    try {
      sender = users.getCurentUser();
      recipient = users.getUserById(recipientId);
    } catch (Exception ex) {
      throw new RuntimeException("User does not exists");
    }

    // Check that request does not exists
    var oldFr = repository.findFriendRequestByUsers(sender.getId(), recipient.getId());

    if (oldFr.isPresent()) {
      throw new RuntimeException("A friend request already exists");
    }

    // Save friend request
    var fr = new FriendRequest(sender.getId(), recipient.getId());
    return toDto(repository.save(fr), sender, recipient);
  }

  public Set<FriendRequestDto> getRecievedFriendRequests() {
    var currentUser = users.getCurentUser();
    return repository.findByRecipientAndStatus(currentUser.getId(), FriendRequestStatus.PENDING)
      .stream()
      .map(this::toDto)
      .collect(Collectors.toSet());
  }

  public void acceptFriendRequest(UUID friendRequestId) {
    var currentUser = users.getCurentUser();

    var fr = repository
      .findByRecipientAndIdAndStatus(currentUser.getId(), friendRequestId, FriendRequestStatus.PENDING)
      .orElseThrow(() -> new RuntimeException("Friend request not found"));

    fr.setStatus(FriendRequestStatus.ACCEPTED);
    repository.save(fr);

    iFriendship.createFriendship(currentUser, users.getUserById(fr.getSender()));
  }

  public void refuseFriendRequest(UUID friendRequestId) {
    var currentUser = users.getCurentUser();

    var fr = repository
      .findByRecipientAndIdAndStatus(currentUser.getId(), friendRequestId, FriendRequestStatus.PENDING)
      .orElseThrow(() -> new RuntimeException("Friend request not found"));

    repository.delete(fr);
  }
}
