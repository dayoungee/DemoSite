package com.web.demo.post.service;

import com.web.demo.post.domain.Posts;
import com.web.demo.post.domain.PostsRepository;
import com.web.demo.post.dto.PostsDto;
import com.web.demo.post.mapper.PostsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final PostsMapper postsMapper;

    private static final int BLOCK_PAGE_NUM_CNT = 4; // 블록에 존재하는 페이지 수
    private static final int PAGE_POST_CNT = 1; // 한 페이지에 존재하는 게시글 수
    @Transactional
    public Long save(PostsDto.Request postsRequestDto) {
        return postsRepository.save(postsMapper.postsRequestToPosts(postsRequestDto)).getId();
    }

    @Transactional(readOnly = true)
    public Page<PostsDto.Response> findPosts(int pageNum, Pageable pageable) {
        pageable = PageRequest.of(pageNum-1, PAGE_POST_CNT, Sort.by("id").descending());
        Page<Posts> pagePosts = postsRepository.findAll(pageable);
        return postsMapper.pagePostsResponseDtoToPagePosts(pagePosts);
    }

    /**
     * 몇 페이지인지 배열에 담기
     * */
    public Integer[] getPageList(Integer pageNum){
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_CNT];
        // 전체 게시글
        Double postsTotalCnt = Double.valueOf(this.getPostsCount());
        // 올림처리 해야함
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCnt/PAGE_POST_CNT)));
        // 마지막 인덱스 나누면 1이됨 그럼 다음 페이지에 갱신되서 이에 대한 처리과정
        Integer startPageValue = ((double)pageNum/(double)BLOCK_PAGE_NUM_CNT) == 1 ? 0 : (pageNum/BLOCK_PAGE_NUM_CNT);

        Integer blockStartPageNum = startPageValue * BLOCK_PAGE_NUM_CNT == 0
                ? 1 : (startPageValue * BLOCK_PAGE_NUM_CNT) + 1;
        Integer blockLastPageNum = Math.min(totalLastPageNum, blockStartPageNum + BLOCK_PAGE_NUM_CNT - 1);
        for(int val = blockStartPageNum, i = 0; val <= blockLastPageNum; val++, i++){
            pageList[i] = val;
        }
        return pageList;
    }

    @Transactional(readOnly = true)
    public Long getPostsCount() {
        return postsRepository.count();
    }

    @Transactional(readOnly = true)
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

    @Transactional
    public int increaseView(Long id){
        return postsRepository.updateView(id);
    }
}
