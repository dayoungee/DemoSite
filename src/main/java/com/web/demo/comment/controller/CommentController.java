package com.web.demo.comment.controller;

import com.web.demo.comment.dto.CommentDto;
import com.web.demo.comment.service.CommentService;
import com.web.demo.config.auth.LoginUser;
import com.web.demo.user.dto.UsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity commentSave(@PathVariable Long id, @RequestBody CommentDto.Request request,
                                      @LoginUser UsersDto.Response user){
        return ResponseEntity.ok(commentService.save(id, user.getNickname(), request));
    }

}
