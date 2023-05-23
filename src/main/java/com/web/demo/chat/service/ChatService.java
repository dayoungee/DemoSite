package com.web.demo.chat.service;

import com.web.demo.chat.domain.ChatRoom;
import com.web.demo.chat.domain.ChatRoomRepository;
import com.web.demo.chat.dto.ChatRoomDto;
import com.web.demo.chat.mapper.ChatMapper;
import com.web.demo.user.domain.Users;
import com.web.demo.user.domain.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRoomRepository repository;
    private final ChatMapper mapper;
    private final UsersRepository usersRepository;
    @Transactional
    public Long create(ChatRoomDto.Request chatRoomDto){
        return repository.save(mapper.chatRoomDtoRequestToChatRoom(chatRoomDto)).getId();
    }

    @Transactional(readOnly = true)
    public List<ChatRoom> findChatRooms() {
        return repository.findAll();
    }


    public ChatRoomDto.Response findChatRoom(Long id){
        return mapper.chatRoomToChatRoomDtoResponse(verifiedChatRoom(id));
    }

    @Transactional
    public int plusUserCnt(Long roomId) {
        return repository.plusUserCnt(roomId);
    }

    @Transactional(readOnly = true)
    public ChatRoom verifiedChatRoom(Long roomId){
        return repository.findById(roomId).orElseThrow(()-> new IllegalArgumentException("찾으시는 채팅방이 존재하지 않습니다. " + roomId));
    }

    public Long addUser(Long roomId, String sender) {
        ChatRoom chatRoom = verifiedChatRoom(roomId);
        Users user = usersRepository.findByNickname(sender);
        chatRoom.addUser(user);
        return user.getId();
    }
}
