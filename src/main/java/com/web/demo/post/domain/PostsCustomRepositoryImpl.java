package com.web.demo.post.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static com.web.demo.post.domain.QPosts.posts;

@RequiredArgsConstructor
public class PostsCustomRepositoryImpl implements PostsCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public int updateView(Long postId) {
        return (int) jpaQueryFactory.update(posts)
                .set(posts.view, posts.view.add(1))
                .where(posts.id.eq(postId)).execute();
    }
}
