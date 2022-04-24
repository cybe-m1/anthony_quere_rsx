package com.talky.socialservice.pushnotification;

import com.google.firebase.messaging.FirebaseMessagingException;

import java.util.UUID;

public interface IPushNotification {
  void sendNotification(UUID userId, Notification notification);
}
