package com.web.demo.chat.domain;

import com.web.demo.common.domain.BaseTimeEntity;
import com.web.demo.user.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ChatRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private int userCount;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int maxUserCnt;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private List<Users> users;

    public void addUser(Users user){
        this.users.add(user);
    }
}
