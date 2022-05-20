package com.talky.socialservice.messages;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
class MessageController {
  private final MessageService messageService;

  @Operation(description = "Send a message, if the reciepient has registered a device, he will recieved a push notification.")
  @PostMapping
  MessageDto postMessage(@RequestBody MessageRequestDto dto) {
    return messageService.sendMessage(dto);
  }

  @Operation(description = "List messages for a specific friendship")
  @GetMapping("/friendship/{friendshipId}")
  Page<MessageDto> listMessages(
    @PathVariable UUID friendshipId,
    @RequestParam(required = false, defaultValue = "AFTER") MessageFetchOption fetch,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
    @RequestParam(required = false, defaultValue = "20") int limit) {

    if (date == null) {
      date = LocalDateTime.now();
    }

    return messageService.listMessages(friendshipId, fetch, date, limit);
  }

}
