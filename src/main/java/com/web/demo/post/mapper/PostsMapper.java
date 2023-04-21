package com.web.demo.post.mapper;

import com.web.demo.post.domain.Posts;
import com.web.demo.post.dto.PostsRequestDto;
import com.web.demo.post.dto.PostsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface PostsMapper {
    Posts postsRequestToPosts(PostsRequestDto postsRequestDto);
    PostsResponseDto postsResponseDtoToPosts(Posts posts);
}
