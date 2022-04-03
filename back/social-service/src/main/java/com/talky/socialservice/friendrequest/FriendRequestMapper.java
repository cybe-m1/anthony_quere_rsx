package com.talky.socialservice.friendrequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface FriendRequestMapper {
  @Mapping(ignore = true, target = "sender")
  @Mapping(ignore = true, target = "recipient")
  FriendRequestDto entityToDto(FriendRequest fr);
}
