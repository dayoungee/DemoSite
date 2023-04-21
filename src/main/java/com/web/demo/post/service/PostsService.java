package com.web.demo.post.service;

import com.web.demo.post.domain.Posts;
import com.web.demo.post.domain.PostsRepository;
import com.web.demo.post.dto.PostsRequestDto;
import com.web.demo.post.dto.PostsResponseDto;
import com.web.demo.post.mapper.PostsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final PostsMapper postsMapper;
    @Transactional
    public Long save(PostsRequestDto postsRequestDto) {
        return postsRepository.save(postsMapper.postsRequestToPosts(postsRequestDto)).getId();
    }

    public List<PostsResponseDto> findPosts() {
        return postsRepository.findAll().stream()
                .map(postsMapper::postsResponseDtoToPosts)
                .collect(Collectors.toList());
    }

    // 리팩토링 필요할듯 소스가 중복됨
    public PostsResponseDto findPost(Long postId) {
        Posts posts =
                postsRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id: " + postId));
        return postsMapper.postsResponseDtoToPosts(posts);
    }

    // 리팩토링 필요할듯 소스가 중복됨
    public void delete(Long postId) {
        Posts posts =
                postsRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id: " + postId));
        postsRepository.delete(posts);
    }
}
