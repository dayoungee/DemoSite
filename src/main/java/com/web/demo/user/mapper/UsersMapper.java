package com.web.demo.user.mapper;

import com.web.demo.config.auth.OAuthAttributes;
import com.web.demo.user.domain.Users;
import com.web.demo.user.dto.UsersDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDto.Response usersToUsersDtoResponse(Users users);
    Users usersDtoRequestToUsers(UsersDto.Request request);
    default Users oauthAttributesToUsers(OAuthAttributes oAuthAttributes){
        if ( oAuthAttributes == null ) {
            return null;
        }

        Users.UsersBuilder users = Users.builder();

        users.email( oAuthAttributes.getEmail() );
        users.nickname( oAuthAttributes.getNickname() );
        users.role( Users.Role.SOCIAL );

        return users.build();
    }
}
