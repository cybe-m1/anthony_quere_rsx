package com.talky.socialservice.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
class MessageController {
  private final MessageService messageService;

  @PostMapping
  MessageDto postMessage(@RequestBody MessageRequestDto dto) {
    return messageService.sendMessage(dto);
  }

}
