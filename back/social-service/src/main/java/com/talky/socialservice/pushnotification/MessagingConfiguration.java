package com.talky.socialservice.pushnotification;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@Configuration
class MessagingConfiguration {

  @Value("${firebase-service-account}")
  private String firebaseServiceAccount;

  @Bean
  FirebaseMessaging firebaseMessaging() throws IOException {
    var credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(Base64.getDecoder().decode(firebaseServiceAccount)));

    var firebaseOptions = FirebaseOptions
      .builder()
      .setCredentials(credentials)
      .build();
    var app = FirebaseApp.initializeApp(firebaseOptions, "talky");
    return FirebaseMessaging.getInstance(app);
  }
}
