package com.web.demo.post.dto;

import com.web.demo.post.domain.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostsRequestDto {
    private String title;
    private String content;
    private String writer;
}
