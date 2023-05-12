package com.web.demo.post.mapper;

import com.web.demo.comment.dto.CommentDto;
import com.web.demo.post.domain.Posts;
import com.web.demo.post.dto.PostsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostsMapper {
    Posts postsRequestToPosts(PostsDto.Request postsRequestDto);


    default PostsDto.Response postsResponseDtoToPosts(Posts posts){
        if ( posts == null ) {
            return null;
        }

        PostsDto.Response.ResponseBuilder response = PostsDto.Response.builder();

        response.userId( posts.getUser().getId() );
        response.id( posts.getId() );
        response.title( posts.getTitle() );
        response.content( posts.getContent() );
        response.writer( posts.getWriter() );
        response.view( posts.getView() );
        response.createdDate( posts.getCreatedDate() );
        response.comments( posts.getComments().stream().map(o -> {
            CommentDto.Response commentDto = CommentDto.Response.builder()
                    .postsId(o.getPosts().getId())
                    .nickname(o.getUser().getNickname())
                    .comment(o.getComment())
                    .modifiedDate(o.getModifiedDate())
                    .createdDate(o.getModifiedDate())
                    .id(o.getId())
                    .build();
            return commentDto;
        }).collect(Collectors.toList()));

        return response.build();
    }

    default Page<PostsDto.Response> pagePostsResponseDtoToPagePosts(Page<Posts> posts){
        return posts.map(o ->
            PostsDto.Response.builder()
                    .id(o.getId())
                    .title(o.getTitle())
                    .writer(o.getWriter())
                    .content(o.getContent())
                    .view(o.getView())
                    .createdDate(o.getCreatedDate())
                    .build());
    }
}
