package com.web.demo.user.service;

import com.web.demo.user.domain.UsersRepository;
import com.web.demo.user.dto.UsersDto;
import com.web.demo.user.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
