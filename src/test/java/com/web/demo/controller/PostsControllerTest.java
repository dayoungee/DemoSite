package com.web.demo.controller;

import com.web.demo.post.domain.Posts;
import com.web.demo.post.domain.PostsRepository;
import com.web.demo.post.dto.PostsDto;
import com.web.demo.post.service.PostsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsControllerTest {
    @Autowired
    private PostsService postsService;
    @Autowired
    private PostsRepository postsRepository;
    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate;

    @AfterEach
    public void clean() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글이 저장된다.")
    public void 게시글_저장_테스트() throws Exception {
        //given
        String title = "제목";
        String writer = "작성자";
        String content = "컨텐츠";
        int view = 3;
        PostsDto.Request postsRequestDto = PostsDto.Request.builder()
                .content(content)
                .title(title)
                .writer(writer)
                .build();
        postsService.save(postsRequestDto);
        //when
        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);
        //then
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getWriter()).isEqualTo(writer);
    }
}
