package com.web.demo.chat.controller;

import com.web.demo.chat.domain.ChatRoom;
import com.web.demo.chat.dto.ChatRoomDto;
import com.web.demo.chat.service.ChatService;
import com.web.demo.config.auth.LoginUser;
import com.web.demo.user.dto.UsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatRoomController {
    private final ChatService chatService;
    @GetMapping("/chat")
    public String chatHome(Model model, @LoginUser UsersDto.Response user){
        List<ChatRoom> chatRooms = chatService.findChatRooms();
        if(user != null){
            model.addAttribute("user", user);
        }

        model.addAttribute("chatRoom", chatRooms);

        return "chat/chat-home";
    }

    @PostMapping("/chat/create")
    public String create(ChatRoomDto.Request chatRoomDto, Model model, @LoginUser UsersDto.Response user){
        chatService.create(chatRoomDto);
        if(user != null){
            model.addAttribute("user", user);
        }
        return "redirect:/chat";
    }

    @GetMapping("/chat/{chat_id}")
    public String chatDetail(@PathVariable("chat_id") Long id, Model model, @LoginUser UsersDto.Response user){
        ChatRoomDto.Response response = chatService.findChatRoom(id);
        model.addAttribute("chatRoom", response);
        if(user != null){
            model.addAttribute("user", user);
        }
        return "/chat/chat-detail";
    }
}
