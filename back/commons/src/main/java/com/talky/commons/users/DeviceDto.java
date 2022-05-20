package com.talky.commons.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class DeviceDto {
  @Schema(description = "If of the user device")
  private String deviceId;
  @Schema(description = "If of the user")
  private UUID user;
}
