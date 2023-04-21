package com.web.demo.domain;

import com.web.demo.post.domain.Posts;
import com.web.demo.post.domain.PostsRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@SpringBootTest
@Log4j2
public class PostsRepositoryTest {
    @Autowired
    private PostsRepository repository;

    // 테스트 전
    @BeforeEach
    public void reset(){
        repository.deleteAll();
    }

    @AfterEach
    public void clear(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("게시글 생성 가져오기 테스트")
    public void 게시글_생성_가져오기() throws Exception {
        //given
        String title = "제목";
        String content = "내용";
        repository.save(Posts.builder().title(title).content(content).writer("테스트").build());
        //when
        List<Posts> list = repository.findAll();
        Posts post = list.get(0);

        //then
        //log.info(">>>>>>>>>>>CreateDate= " + post.getCreatedDate() + " modifiedDate = " + post.getModifiedDate());
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getTitle()).isEqualTo(title);
    }
}
