package com.web.demo.post.controller;

import com.web.demo.post.domain.Posts;
import com.web.demo.post.dto.PostsRequestDto;
import com.web.demo.post.dto.PostsResponseDto;
import com.web.demo.post.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostsController {
    private final PostsService postsService;
    @GetMapping("/")
    public String list(Model model){
        List<PostsResponseDto> posts = postsService.findPosts();
        model.addAttribute("posts",posts);
        return "home";
    }

    @GetMapping("/posts/{post_id}")
    public String getPost(@PathVariable("post_id") Long postId, Model model){
        PostsResponseDto postsResponseDto = postsService.findPost(postId);
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
    public String save(PostsRequestDto postsRequestDto){
        postsService.save(postsRequestDto);
        return "redirect:/";
    }
}
