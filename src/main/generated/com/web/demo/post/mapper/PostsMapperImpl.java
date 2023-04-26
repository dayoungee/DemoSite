package com.web.demo.post.mapper;

import com.web.demo.post.domain.Posts;
import com.web.demo.post.dto.PostsDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-25T23:43:41+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class PostsMapperImpl implements PostsMapper {

    @Override
    public Posts postsRequestToPosts(PostsDto.Request postsRequestDto) {
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
    public PostsDto.Response postsResponseDtoToPosts(Posts posts) {
        if ( posts == null ) {
            return null;
        }

        PostsDto.Response.ResponseBuilder response = PostsDto.Response.builder();

        response.id( posts.getId() );
        response.title( posts.getTitle() );
        response.content( posts.getContent() );
        response.writer( posts.getWriter() );
        response.view( posts.getView() );
        response.createdDate( posts.getCreatedDate() );

        return response.build();
    }
}
