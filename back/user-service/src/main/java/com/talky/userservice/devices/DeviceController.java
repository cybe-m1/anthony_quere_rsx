package com.talky.userservice.devices;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping
class DeviceController {
  private final DeviceService service;

  @GetMapping("/api/v1/devices/user/{userId}")
  public Set<DeviceDto> getUserDevices(@PathVariable UUID userId) {
    return service.getUserDevices(userId);
  }
}
