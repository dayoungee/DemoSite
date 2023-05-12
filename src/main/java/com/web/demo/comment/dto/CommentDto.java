package com.web.demo.comment.dto;

import com.web.demo.post.domain.Posts;
import com.web.demo.user.domain.Users;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentDto {

    @Getter
    @Builder
    public static class Request{
        private Long id;
        private String comment;
        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private Users user;
        private Posts posts;

        public void setUser(Users user){
            this.user = user;
        }

        public void setPosts(Posts posts){
            this.posts = posts;
        }
    }

    @Getter
    @Builder
    public static class Response{
        private Long id;
        private String comment;
        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String nickname;
        private Long postsId;
    }
}
