package com.talky.userservice.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
interface UserMapper {
  User createUserRequestDtoToUser(CreateUserRequestDto dto);

  void updateUser(UpdateUserRequestDto dto, @MappingTarget User user);
}
