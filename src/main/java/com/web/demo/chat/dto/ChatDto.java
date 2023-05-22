package com.web.demo.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    public enum MessageType{
        ENTER, TALK, LEAVE;
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private String time;
}
