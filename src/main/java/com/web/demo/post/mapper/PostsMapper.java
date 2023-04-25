package com.web.demo.post.mapper;

import com.web.demo.post.domain.Posts;
import com.web.demo.post.dto.PostsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostsMapper {
    Posts postsRequestToPosts(PostsDto.Request postsRequestDto);
    PostsDto.Response postsResponseDtoToPosts(Posts posts);
}
