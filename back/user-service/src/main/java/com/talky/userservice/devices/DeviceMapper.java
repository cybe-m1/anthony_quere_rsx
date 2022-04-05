package com.talky.userservice.devices;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface DeviceMapper {

  DeviceDto toDto(Device device);
}
