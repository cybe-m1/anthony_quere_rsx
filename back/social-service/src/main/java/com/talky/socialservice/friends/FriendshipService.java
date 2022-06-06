package com.talky.socialservice.friends;

import com.talky.commons.social.FriendshipDto;
import com.talky.commons.users.IUsers;
import com.talky.commons.users.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class FriendshipService implements IFriendship {
  private final FriendshipRepository repository;
  private final FriendshipMapper mapper;
  private final IUsers users;

  public FriendshipDto createFriendship(UserDto friendA, UserDto friendB) {

    var fs = new Friendship(friendA.getId(), friendB.getId());
    fs.setCreationDate(LocalDateTime.now());
    fs = repository.save(fs);

    var dto = mapper.entityToDto(fs);

    dto.setFriends(Set.of(friendA, friendB));

    return dto;
  }

  public FriendshipDto getFriendshipByFriend(UUID friendId) {
    var currentUser = users.getCurentUser();

    return repository.findByFriends(currentUser.getId(), friendId)
      .map(mapper::entityToDto)
      .orElse(null);
  }

  @Override
  public FriendshipDto getFriendship(UUID friendship) {
    var fs = repository.findById(friendship).orElseThrow();

    var dto = mapper.entityToDto(fs);

    dto.setFriends(Set.of(
      users.getUserById(fs.getFriendA()),
      users.getUserById(fs.getFriendB())
    ));

    return dto;
  }

  List<FriendDto> getFriends(Pageable pageable) {
    var currentUser = users.getCurentUser();
    var friendships = repository.findByFriendAOrFriendB(currentUser.getId(), currentUser.getId(), pageable).getContent();

    return friendships.stream().map(friendship -> {
      var friendId = friendship.getFriendA().equals(currentUser.getId()) ? friendship.getFriendB() : friendship.getFriendA();
      var friendUserDto = users.getUserById(friendId);
      return new FriendDto(friendship.getId(), friendUserDto);
    }).toList();
  }

  Page<FriendDto> getFriendsWithPagination(Pageable pageable) {
    var currentUser = users.getCurentUser();
    var friendships = repository.findByFriendAOrFriendB(currentUser.getId(), currentUser.getId(), pageable);

    return friendships.map(friendship -> {
      var friendId = friendship.getFriendA().equals(currentUser.getId()) ? friendship.getFriendB() : friendship.getFriendA();
      var friendUserDto = users.getUserById(friendId);
      return new FriendDto(friendship.getId(), friendUserDto);
    });
  }


}
