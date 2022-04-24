package com.talky.socialservice.messages;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface MessageMapper {

  MessageDto entitytoDto(Message message);

  Message requestToEntity(MessageRequestDto dto);
}
