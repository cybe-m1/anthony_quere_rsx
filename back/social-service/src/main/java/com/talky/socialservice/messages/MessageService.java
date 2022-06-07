package com.talky.socialservice.messages;


import com.talky.commons.assets.IAssets;
import com.talky.commons.users.IUsers;
import com.talky.socialservice.friends.IFriendship;
import com.talky.socialservice.pushnotification.IPushNotification;
import com.talky.socialservice.pushnotification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
class MessageService {
  private final MessageRepository messageRepository;
  private final MessageMapper mapper;
  private final IUsers users;
  private final IFriendship friendship;
  private final IPushNotification pushNotificationsService;
  private final IAssets assets;

  public MessageDto sendMessage(MessageRequestDto dto) {
    var currentUser = users.getCurentUser();

    var fs = friendship.getFriendship(dto.getFriendshipId());

    if (!fs.isPartOfFriendship(currentUser.getId())) {
      throw new RuntimeException("Unauthorized");
    }

    var message = mapper.requestToEntity(dto);
    message.setAuthor(currentUser.getId());
    message.setCreatedAt(LocalDateTime.now());

    var messageDto = mapper.entitytoDto(messageRepository.save(message));

    // Send notification
    var notification = new Notification("New message from %s".formatted(currentUser.getDisplayedName()), message.getContent(), messageDto);

    fs.getFriends()
      .stream()
      .filter(f -> !f.getId().equals(currentUser.getId()))
      .forEach(user ->
        pushNotificationsService.sendNotification(user.getId(), notification)
      );

    return messageDto;
  }

  public Page<MessageDto> listMessages(UUID friendshipId, MessageFetchOption fetchOption, LocalDateTime dateTime, int limit) {
    var page = Pageable.ofSize(limit);

    var currentUser = users.getCurentUser();

    var fs = friendship.getFriendship(friendshipId);
    if (!fs.isPartOfFriendship(currentUser.getId())) {
      throw new RuntimeException("UAUTHORIZED");
    }

    var messages = switch (fetchOption) {
      case AFTER -> messageRepository.getByFriendshipIdAndCreatedAtAfterOrderByCreatedAtAsc(friendshipId, dateTime, page);
      case BEFORE -> messageRepository.getByFriendshipIdAndCreatedAtBeforeOrderByCreatedAtDesc(friendshipId, dateTime, page);
    };

    return messages.map(mapper::entitytoDto);
  }

}
