package com.web.demo.comment.mapper;

import com.web.demo.comment.domain.Comment;
import com.web.demo.comment.domain.CommentRepository;
import com.web.demo.comment.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentDtoRequestToComment(CommentDto.Request request);
    @Mapping(source = "comment.user.nickname", target = "nickname")
    @Mapping(source = "comment.posts.id", target = "postsId")
    @Mapping(source = "comment.user.id", target = "userId")
    CommentDto.Response commentToCommentDtoResponse(Comment comment);
}
