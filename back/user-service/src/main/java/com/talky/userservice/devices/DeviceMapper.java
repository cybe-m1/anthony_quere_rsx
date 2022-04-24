package com.talky.userservice.devices;

import com.talky.commons.users.DeviceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface DeviceMapper {

  DeviceDto toDto(Device device);
}
