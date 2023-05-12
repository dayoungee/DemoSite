package com.web.demo.post.domain;

import com.web.demo.comment.domain.Comment;
import com.web.demo.common.domain.BaseTimeEntity;
import com.web.demo.user.domain.Users;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @Column(nullable = false)
    private String writer;
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @ManyToOne(fetch = FetchType.LAZY) // EAGER 전략은 N+1 문제를 야기할 수 있어 LAZY 전략으로
    @JoinColumn(name = "users_id")
    private Users user;

    // mappedBy comment 엔티티의 posts 필드에 매핑
    // 일대다관계에서 cascade를 주로 사용한다.
    // 게시글이 삭제되면 댓글도 삭제되어야 하니 cascade 옵션 사용
    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> comments;

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
