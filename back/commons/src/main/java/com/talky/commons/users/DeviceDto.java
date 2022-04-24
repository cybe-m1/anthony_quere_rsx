package com.talky.commons.users;

import lombok.Data;

import java.util.UUID;

@Data
public class DeviceDto {
  private String deviceId;
  private UUID user;
}
