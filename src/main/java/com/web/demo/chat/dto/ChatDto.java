package com.web.demo.chat.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    public enum MessageType{
        ENTER, TALK, LEAVE;
    }
    private MessageType type;
    private Long roomId;
    private String sender;
    private String message;
    private String time;
}
