package com.web.demo.post.mapper;

import com.web.demo.post.domain.Posts;
import com.web.demo.post.dto.PostsDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PostsMapper {
    Posts postsRequestToPosts(PostsDto.Request postsRequestDto);
    PostsDto.Response postsResponseDtoToPosts(Posts posts);
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
