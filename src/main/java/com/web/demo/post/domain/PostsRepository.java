package com.web.demo.post.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostsRepository extends JpaRepository<Posts, Long>, PostsCustomRepository{
    Page<Posts> findByTitleContaining(String keyword, Pageable pageable);
    long countByTitleContaining(String keyword);
}
