package com.web.demo.post.domain;

import com.web.demo.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

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

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
