package com.web.demo.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Modifying
    @Query("UPDATE Posts p set p.view = p.view + 1 where p.id = :id")
    int updateView(@Param("id") Long postId);
}
