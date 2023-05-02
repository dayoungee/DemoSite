package com.web.demo.user.controller;

import com.web.demo.user.dto.UsersDto;
import com.web.demo.user.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/auth/signup")
    public String signupPage(){
        return "user/signup";
    }

    @GetMapping("/auth/signup/email")
    public String emailSignup(){
        return "user/emailSignup";
    }

    @PostMapping("/auth/signup/email")
    public String userSave(UsersDto.Request userDto){
        usersService.join(userDto);

        return "redirect:/";
    }

    @GetMapping("/auth/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
