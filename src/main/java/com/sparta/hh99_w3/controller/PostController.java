package com.sparta.hh99_w3.controller;

import com.sparta.hh99_w3.config.UserDetailsImpl;
import com.sparta.hh99_w3.dto.PostRequestDto;
import com.sparta.hh99_w3.models.Post;
import com.sparta.hh99_w3.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping()
    public void createCourse(@RequestBody PostRequestDto requestDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(requestDto, userDetails.getUser());
    }

    // 게시글 조회
    @GetMapping("/")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public Post getEachPost(@PathVariable Long id) {
        return postService.getEachPost(id);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.update(id, requestDto, userDetails.getUser());
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public Long deletePost(@PathVariable Long id,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(id, userDetails.getUser());
    }



}