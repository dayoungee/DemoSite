package com.web.demo.post.service;

import com.web.demo.post.domain.Posts;
import com.web.demo.post.domain.PostsRepository;
import com.web.demo.post.dto.PostsDto;
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
    public Long save(PostsDto.Request postsRequestDto) {
        return postsRepository.save(postsMapper.postsRequestToPosts(postsRequestDto)).getId();
    }

    @Transactional
    public List<PostsDto.Response> findPosts() {
        return postsRepository.findAll().stream()
                .map(postsMapper::postsResponseDtoToPosts)
                .collect(Collectors.toList());
    }

    // 리팩토링 필요할듯 소스가 중복됨
    public PostsDto.Response findPost(Long postId) {
        Posts posts = verifiedPost(postId);

        return postsMapper.postsResponseDtoToPosts(posts);
    }

    @Transactional
    public void delete(Long postId) {
        Posts posts = verifiedPost(postId);
        postsRepository.delete(posts);
    }

    @Transactional
    public Long update(Long postId, PostsDto.Request postsRequestDto){
        Posts posts = verifiedPost(postId);
        posts.update(postsRequestDto.getTitle(), postsRequestDto.getContent());
        return postId;
    }

    public Posts verifiedPost(Long postId){
        return postsRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id: " + postId));
    }
}
