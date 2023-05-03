package com.web.demo.user.service;

import com.web.demo.user.domain.UsersRepository;
import com.web.demo.user.dto.UsersDto;
import com.web.demo.user.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final BCryptPasswordEncoder encoder;


    @Transactional
    public Long join(UsersDto.Request usersDto){

        usersDto.setPassword(encoder.encode(usersDto.getPassword()));
        return usersRepository.save(usersMapper.usersDtoRequestToUsers(usersDto)).getId();
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for(FieldError error : errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

/*    @Transactional(readOnly = true)
    public void checkEmailDuplication(UsersDto.Request dto){
        boolean emailDuplicate = usersRepository.existsByEmail(usersMapper.usersDtoRequestToUsers(dto).getEmail());
        if(emailDuplicate){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    @Transactional(readOnly = true)
    public void checkNicknameDuplication(UsersDto.Request dto){
        boolean nicknameDuplicate = usersRepository.existsByNickname(usersMapper.usersDtoRequestToUsers(dto).getEmail());
        if(nicknameDuplicate){
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }*/
}
