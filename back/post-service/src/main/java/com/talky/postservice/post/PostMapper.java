package com.talky.postservice.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
interface PostMapper {

  @Mappings({
    @Mapping(target = "author", ignore = true)
  })
  PostDto toDto(Post post);
}
