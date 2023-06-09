package com.web.demo.post.dto;

import com.web.demo.comment.dto.CommentDto;
import com.web.demo.user.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class PostsDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private String title;
        private String content;
        private String writer;
        private Users user;

        public void setUser(Users user){
            this.user = user;
        }
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

        private Long userId;
        private List<CommentDto.Response> comments;
    }
}
