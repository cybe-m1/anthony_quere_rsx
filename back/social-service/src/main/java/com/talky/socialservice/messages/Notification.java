package com.talky.socialservice.messages;

public record Notification(String title, String message) {
  public com.google.firebase.messaging.Notification toFirebaseNotification() {
    return com.google.firebase.messaging.Notification
      .builder()
      .setTitle(this.title())
      .setBody(this.message())
      .build();
  }
}
