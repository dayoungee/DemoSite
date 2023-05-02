package com.web.demo.user.domain;

import com.web.demo.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 15)
    private String nickname;
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Getter
    @RequiredArgsConstructor
    public enum Role{
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");

        private final String value;
    }

}
