package com.talky.userservice.devices;

import lombok.Data;

import java.util.UUID;

@Data
public class DeviceDto {
  private String deviceId;
  private UUID user;
}
