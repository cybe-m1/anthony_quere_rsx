package com.talky.postservice.post;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface PostMapper {

  PostDto toDto(Post post);
}
