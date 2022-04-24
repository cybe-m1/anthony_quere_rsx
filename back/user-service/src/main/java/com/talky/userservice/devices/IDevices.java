package com.talky.userservice.devices;

import com.talky.commons.users.DeviceDto;

import java.util.UUID;

public interface IDevices {
  DeviceDto assignDeviceToUser(String deviceId, UUID userId);
}
