package com.talky.userservice.devices;

import com.talky.commons.users.DeviceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class DeviceService {
  private final DeviceRepository deviceRepository;
  private final DeviceMapper mapper;

  Set<DeviceDto> getUserDevices(UUID userId) {
    return deviceRepository.findByUserId(userId).stream().map(mapper::toDto).collect(Collectors.toSet());
  }

  public DeviceDto assignDeviceToUser(String deviceId, UUID userId) {
    var optionalPreviousDevice = deviceRepository.findById(deviceId);

    if (optionalPreviousDevice.isPresent()) {
      var previousDevice = optionalPreviousDevice.get();
      if (previousDevice.getUserId().equals(userId)) return mapper.toDto(previousDevice);
      deviceRepository.delete(previousDevice);
    }
    var d = new Device(deviceId, userId);

    return mapper.toDto(deviceRepository.save(d));
  }
}
