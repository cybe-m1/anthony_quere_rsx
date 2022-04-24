package com.talky.socialservice.friends;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FriendshipMapper {
  @Mapping(ignore = true, target = "friends")
  FriendshipDto entityToDto(Friendship fs);
}
