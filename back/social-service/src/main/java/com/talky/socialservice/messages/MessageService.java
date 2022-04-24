package com.talky.socialservice.messages;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.talky.commons.users.IUsers;
import com.talky.socialservice.friends.IFriendship;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
class MessageService {
  private final MessageRepository messageRepository;
  private final MessageMapper mapper;
  private final IUsers users;
  private final IFriendship friendship;
  private final PushNotificationsService pushNotificationsService;

  public MessageDto sendMessage(MessageRequestDto dto) {
    var currentUser = users.getCurentUser();

    var fs = friendship.getFriendship(dto.getFriendshipId());

    if (!fs.isPartOfFriendship(currentUser.getId())) {
      throw new RuntimeException("Unauthorized");
    }

    var message = mapper.requestToEntity(dto);
    message.setAuthor(currentUser.getId());
    message.setCreatedAt(LocalDateTime.now());

    var messageDto =  mapper.entitytoDto(messageRepository.save(message));

    // Send notification
    var notification = new Notification("New message from %s".formatted(currentUser.getDisplayedName()), message.getContent(), messageDto);

    fs.getFriends()
      .stream()
      .filter(f -> !f.getId().equals(currentUser.getId()))
      .forEach(user ->  {
        try {
          pushNotificationsService.sendNotification(user.getId(), notification);
        } catch (FirebaseMessagingException ex) {
          log.warn("Fail to send firebase notification : {}", ex.getMessagingErrorCode());
        }
      });

    return messageDto;
  }

}
