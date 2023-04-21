package com.web.demo.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostsResponseDto {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private int view;

    private LocalDateTime createdDate;
}
