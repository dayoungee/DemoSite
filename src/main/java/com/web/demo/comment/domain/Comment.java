package com.web.demo.comment.domain;

import com.web.demo.common.domain.BaseTimeEntity;
import com.web.demo.post.domain.Posts;
import com.web.demo.user.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comments")
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    // 다대일 관계에서 외래키를 가져야한다.
    // 다대일 관계에서 다 쪽이 주인이어야 함
    @ManyToOne
    @JoinColumn(name = "posts_id") // 외래키
    private Posts posts;

}
