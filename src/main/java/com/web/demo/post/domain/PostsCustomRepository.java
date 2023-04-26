package com.web.demo.post.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostsCustomRepository {
    int updateView(Long postId);
}
