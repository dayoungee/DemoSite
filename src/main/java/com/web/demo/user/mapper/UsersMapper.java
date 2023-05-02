package com.web.demo.user.mapper;

import com.web.demo.user.domain.Users;
import com.web.demo.user.dto.UsersDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDto.Response usersToUsersDtoResponse(Users users);
    Users usersDtoRequestToUsers(UsersDto.Request request);
}
