package com.web.demo.chat.mapper;

import com.web.demo.chat.domain.ChatRoom;
import com.web.demo.chat.dto.ChatRoomDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    ChatRoom chatRoomDtoRequestToChatRoom(ChatRoomDto.Request chatRoomDto);
    ChatRoomDto.Response chatRoomToChatRoomDtoResponse(ChatRoom chatRoomDto);
}
