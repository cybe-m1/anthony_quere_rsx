package com.talky.socialservice.messages;


import java.util.Map;

public record Notification(String title, String message, MessageData body) {

  public com.google.firebase.messaging.Notification toFirebaseNotification() {
    return com.google.firebase.messaging.Notification
      .builder()
      .setTitle(this.title())
      .setBody(this.message())
      .build();
  }
}
