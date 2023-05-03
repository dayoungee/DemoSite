package com.web.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.demo.config.auth.CustomUserDetails;
import com.web.demo.post.domain.Posts;
import com.web.demo.post.domain.PostsRepository;
import com.web.demo.post.dto.PostsDto;
import com.web.demo.post.service.PostsService;
import com.web.demo.user.domain.Users;
import com.web.demo.user.domain.UsersRepository;
import com.web.demo.user.service.UsersService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PostsControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    public String getURI(){
        return "http://localhost:" + port;
    }

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void clear(){
        postsRepository.deleteAll();
    }
    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("게시글이 저장된다.")
    public void posts_저장() throws Exception {
        //given
        String title = "제목";
        String writer = "작성자";
        String content = "컨텐츠";

        //when
        mvc.perform(
                        post(getURI() + "/posts/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .param("title", title)
                                .param("writer", writer)
                                .param("content", content)
                                .with(csrf()));

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);
        //then
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getWriter()).isEqualTo(writer);
    }
}
