package com.web.demo.post.controller;

import com.web.demo.common.domain.Pagination;
import com.web.demo.post.dto.PostsDto;
import com.web.demo.post.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostsController {
    private final PostsService postsService;
    @GetMapping("/")
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum
    , Pageable pageable){
        Page<PostsDto.Response> posts = postsService.findPosts(pageNum, pageable);
        Integer[] pageList = postsService.getPageList(pageNum, postsService.getPostsCount());
        Pagination pagination = new Pagination(pageable, posts, pageNum);
        model.addAttribute("posts",posts);
        model.addAttribute("pageList",pageList);
        model.addAttribute("pagination",pagination);
        return "home";
    }

    @GetMapping("/posts/{post_id}")
    public String getPost(@PathVariable("post_id") Long postId, Model model){
        PostsDto.Response postsResponseDto = postsService.findPost(postId);
        postsService.increaseView(postId);
        model.addAttribute("postDto",postsResponseDto);
        return "posts/detail";
    }
    @DeleteMapping("/posts/{post_id}")
    public String deletePost(@PathVariable("post_id") Long postId) {
        postsService.delete(postId);
        return "redirect:/";
    }

    @GetMapping("/posts/new")
    public String write(){
        return "posts/write";
    }

    @PostMapping("/posts/new")
    public String save(PostsDto.Request postsRequestDto){
        postsService.save(postsRequestDto);
        return "redirect:/";
    }

    @GetMapping("/posts/edit/{post_id}")
    public String edit(@PathVariable("post_id") Long id, Model model){
        PostsDto.Response postsResponseDto = postsService.findPost(id);
        model.addAttribute("postDto", postsResponseDto);
        return "posts/update";
    }

    @PutMapping("/posts/edit/{post_id}")
    public String edit(@PathVariable("post_id") Long id, PostsDto.Request postsRequestDto, Model model){
        postsService.update(id, postsRequestDto);
        PostsDto.Response postsResponseDto = postsService.findPost(id);
        model.addAttribute("postDto", postsResponseDto);
        return "posts/detail";
    }

    @GetMapping("/posts/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model,
                         @RequestParam(value="page", defaultValue = "1") Integer pageNum, Pageable pageable){
        Page<PostsDto.Response> posts = postsService.findSearchPosts(keyword, pageNum, pageable);
        Integer[] pageList = postsService.getPageList(pageNum, postsService.getSearchPostsCount(keyword));
        Pagination pagination = new Pagination(pageable, posts, pageNum);
        model.addAttribute("posts",posts);
        model.addAttribute("pageList",pageList);
        model.addAttribute("pagination",pagination);
        model.addAttribute("keyword",keyword);

        return "posts/search";
    }
}
