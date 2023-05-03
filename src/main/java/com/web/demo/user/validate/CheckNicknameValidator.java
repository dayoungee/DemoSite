package com.web.demo.user.validate;

import com.web.demo.user.domain.UsersRepository;
import com.web.demo.user.dto.UsersDto;
import com.web.demo.user.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckNicknameValidator extends AbstractValidator<UsersDto.Request>{
    private final UsersRepository usersRepository;
    private final UsersMapper mapper;
    @Override
    protected void doValidate(UsersDto.Request dto, Errors errors) {
        if(usersRepository.existsByNickname(mapper.usersDtoRequestToUsers(dto).getNickname())){
            errors.rejectValue("nickname","닉네임 중복 오류", "이미 사용중인 닉네임입니다.");
        }
    }
}
