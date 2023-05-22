package com.web.demo.chat.service;

import com.web.demo.chat.domain.ChatRoom;
import com.web.demo.chat.domain.ChatRoomRepository;
import com.web.demo.chat.dto.ChatRoomDto;
import com.web.demo.chat.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRoomRepository repository;
    private final ChatMapper mapper;
    @Transactional
    public Long create(ChatRoomDto.Request chatRoomDto){
        return repository.save(mapper.chatRoomDtoRequestToChatRoom(chatRoomDto)).getId();
    }

    @Transactional(readOnly = true)
    public List<ChatRoom> findChatRoom() {
        return repository.findAll();
    }
}
