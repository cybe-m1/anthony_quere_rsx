package com.talky.socialservice.messages;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PushNotificationsService {
  private final FirebaseMessaging firebaseMessaging;

  public String sendNotification(String token) throws FirebaseMessagingException {
    Notification notification = Notification
      .builder()
      .setTitle("Radm title")
      .setBody("Lorem ipsum dolor sit ahmet")
      .build();

    Message message = Message
      .builder()
      .setToken(token)
      .setNotification(notification)
      .build();

    return firebaseMessaging.send(message);
  }
}
