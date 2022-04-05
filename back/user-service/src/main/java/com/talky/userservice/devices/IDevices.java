package com.talky.userservice.devices;

import java.util.UUID;

public interface IDevices {
  DeviceDto assignDeviceToUser(String deviceId, UUID userId);
}
