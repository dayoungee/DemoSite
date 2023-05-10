package com.web.demo.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class PostsDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private String title;
        private String content;
        private String writer;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Response {

        private Long id;

        private String title;

        private String content;

        private String writer;

        private int view;

        private String createdDate;
    }
}
