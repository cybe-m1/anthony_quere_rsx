package com.talky.socialservice.friends;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FriendshipMapper {
  @Mapping(ignore = true, target = "friendA")
  @Mapping(ignore = true, target = "friendB")
  FriendshipDto entityToDto(Friendship fs);
}
