package com.web.demo.comment.service;

import com.web.demo.comment.domain.Comment;
import com.web.demo.comment.domain.CommentRepository;
import com.web.demo.comment.dto.CommentDto;
import com.web.demo.comment.mapper.CommentMapper;
import com.web.demo.post.domain.Posts;
import com.web.demo.post.domain.PostsRepository;
import com.web.demo.user.domain.Users;
import com.web.demo.user.domain.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;
    private final UsersRepository usersRepository;
    private final PostsRepository postsRepository;
    public Long save(Long id, String nickname, CommentDto.Request request) {
        Users user = usersRepository.findByNickname(nickname);
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다.  " + id));

        request.setUser(user);
        request.setPosts(posts);

        Comment comment = mapper.commentDtoRequestToComment(request);
        commentRepository.save(comment);

        return request.getId();
    }
}
