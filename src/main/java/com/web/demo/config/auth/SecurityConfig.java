package com.web.demo.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService customUserDetailsService;

    // 로그인 실패 핸들러 의존성 주입
    private final AuthenticationFailureHandler customFailureHandler;
    // OAuth
    private final CustomOAuth2UserService customOAuth2UserService;

    // 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager 를 통해 모든 인증이 처리됨. 그래서 생성.
    // 어떤 해쉬로 암호화 했는지 알려주기 위해서 encoder 아규먼트로 전달.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
    }

    // 인증을 무시할 경로 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    // 보안을 구성
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()// csrf토큰이 없이 요청하면 해당 요청을 막기 때문에 비활성화
                .authorizeRequests()//request에 따라 접근을 제한
                .antMatchers("/", "/auth/**", "/posts/detail/**", "/posts/search/**")// 특정 경로에 대해서
                .permitAll()// 접근이 가능하다.
                .anyRequest()// 그 외의 경로는
                .authenticated()// 인증된 사용자만 접근이 가능
                .and()
                .formLogin()
                .loginPage("/auth/login") // /login으로 설정하면 기본으로 제공해주는 로그인 폼을 사용할 수 있지만, 난 커스텀을 사용할 것임
                .loginProcessingUrl("/loginProc") // 시큐리티가 해당 주소로 오는 요청을 낚아채서 수행한다
                .failureHandler(customFailureHandler)
                .defaultSuccessUrl("/") // 로그인 성공 시 이동 페이지
                .and()
                .logout()// 로그아웃 지원
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")// 로그아웃 성공시 이동 페이지
                .and()
                .oauth2Login()
                .userInfoEndpoint() // OAuth2 로그인 성공 후 가져올 설정
                .userService(customOAuth2UserService);// 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명식

    }
}
