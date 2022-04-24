package com.talky.socialservice.messages;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestController {
  private final PushNotificationsService pushNotificationsService;

  @GetMapping("/trigger")
  public void trigger() throws FirebaseMessagingException {
    pushNotificationsService.sendNotification(UUID.fromString("6e7e1054-76d9-4232-bb7d-75a27739ff70"), new Notification("Some title", "Some content"));
  }
}

