package com.web.demo.config.auth;

import com.web.demo.user.domain.Users;
import com.web.demo.user.domain.UsersRepository;
import com.web.demo.user.dto.UsersDto;
import com.web.demo.user.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    private final HttpSession session;
    private final UsersMapper usersMapper;

    /* 이메일이 DB에 있는지 확인 */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + email));

        session.setAttribute("user", usersMapper.usersToUsersDtoResponse(user));

        /* 시큐리티 세션에 유저 정보 저장 */
        return new CustomUserDetails(user);
    }
}
