package com.talky.socialservice.messages;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.talky.commons.users.IUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class PushNotificationsService {
  private final FirebaseMessaging firebaseMessaging;
  private final IUsers iUsers;

  public void sendNotification(UUID userId, Notification notification) throws FirebaseMessagingException {

    // Build notification
    var fbNotification = notification.toFirebaseNotification();

    // Access user device tokens
    var tokens = iUsers.getUserDevices(userId);

    // Send messages to all devices
    var messages = tokens.stream().map(token -> Message
      .builder()
      .setToken(token)
      .setNotification(fbNotification)
      .putAllData(notification.body().toMessageData())
      .build()).toList();

    firebaseMessaging.sendAll(messages);
  }
}
