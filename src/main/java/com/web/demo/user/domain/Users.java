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

    @Column(length = 100)
    private String password;

    @Column(nullable = false, length = 15)
    private String nickname;
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Getter
    @RequiredArgsConstructor
    public enum Role {
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN"),
        SOCIAL("ROLE_SOCIAL"); // OAuth


        private final String value;
    }

    /* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트하고
     * 기존 데이터는 그대로 보존하도록 예외처리 */
    public Users updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }

}
