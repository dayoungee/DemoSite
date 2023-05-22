package com.web.demo.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.web.demo.user.domain.Users;
import lombok.*;

import java.util.List;


public class ChatRoomDto {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Request{

        private String name;

        private String maxUserCnt;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Response{
        private String id;
        private String name;
        private int userCount;

        private int maxUserCnt;
    }
}
