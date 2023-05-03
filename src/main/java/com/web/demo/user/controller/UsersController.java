package com.web.demo.user.controller;

import com.web.demo.user.validate.CheckEmailValidator;
import com.web.demo.user.validate.CheckNicknameValidator;
import com.web.demo.user.dto.UsersDto;
import com.web.demo.user.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UsersController {
    private final UsersService usersService;
    private final CheckEmailValidator checkEmailValidator;
    private final CheckNicknameValidator checkNicknameValidator;

    // 커스텀 검증
    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkEmailValidator);
        binder.addValidators(checkNicknameValidator);
    }
    @GetMapping("/auth/signup")
    public String signupPage(){
        return "user/signup";
    }

    @GetMapping("/auth/signup/email")
    public String emailSignup(){
        return "user/emailSignup";
    }

    @PostMapping("/auth/signup/email")
    public String userSave(@Valid UsersDto.Request userDto, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("userDto", userDto);

            Map<String, String> validatorResult = usersService.validateHandling(errors);
            for(String key : validatorResult.keySet()){
                model.addAttribute(key, validatorResult.get(key));
            }
            return "user/emailSignup";
        }
        usersService.join(userDto);

        return "redirect:/";
    }

    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model){
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
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
