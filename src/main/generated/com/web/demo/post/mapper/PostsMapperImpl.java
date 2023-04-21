package com.web.demo.post.mapper;

import com.web.demo.post.domain.Posts;
import com.web.demo.post.dto.PostsRequestDto;
import com.web.demo.post.dto.PostsResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-22T00:59:07+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class PostsMapperImpl implements PostsMapper {

    @Override
    public Posts postsRequestToPosts(PostsRequestDto postsRequestDto) {
        if ( postsRequestDto == null ) {
            return null;
        }

        Posts.PostsBuilder posts = Posts.builder();

        posts.title( postsRequestDto.getTitle() );
        posts.content( postsRequestDto.getContent() );
        posts.writer( postsRequestDto.getWriter() );

        return posts.build();
    }

    @Override
    public PostsResponseDto postsResponseDtoToPosts(Posts posts) {
        if ( posts == null ) {
            return null;
        }

        PostsResponseDto.PostsResponseDtoBuilder postsResponseDto = PostsResponseDto.builder();

        postsResponseDto.id( posts.getId() );
        postsResponseDto.title( posts.getTitle() );
        postsResponseDto.content( posts.getContent() );
        postsResponseDto.writer( posts.getWriter() );
        postsResponseDto.view( posts.getView() );
        postsResponseDto.createdDate( posts.getCreatedDate() );

        return postsResponseDto.build();
    }
}
