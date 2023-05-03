package com.web.demo.user.validate;

import com.web.demo.user.domain.UsersRepository;
import com.web.demo.user.dto.UsersDto;
import com.web.demo.user.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckEmailValidator extends AbstractValidator<UsersDto.Request>{
    private final UsersRepository usersRepository;
    private final UsersMapper mapper;
    @Override
    protected void doValidate(UsersDto.Request dto, Errors errors) {
        if(usersRepository.existsByEmail(mapper.usersDtoRequestToUsers(dto).getEmail())){
            errors.rejectValue("email","이메일 중복 오류", "이미 사용중인 이메일입니다.");
        }
    }
}
