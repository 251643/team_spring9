package com.sparta.hh99_w3.controller;

import com.sparta.hh99_w3.config.UserDetailsImpl;
import com.sparta.hh99_w3.dto.CommentSaveDto;
import com.sparta.hh99_w3.dto.CommentUpdateDto;
import com.sparta.hh99_w3.models.Comment;
import com.sparta.hh99_w3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class CommentController {

    private final CommentService commentService;

    // 댓글 조회
    @GetMapping("comment/{id}")
    public Comment getComment(@PathVariable("id") Long id) {
        return commentService.getComment(id);
    }

    @PostMapping("/comment/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void commentSave(@PathVariable("postId") Long postId,
                            @RequestBody CommentSaveDto commentSaveDto,
                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.commentSave(postId, commentSaveDto, userDetails.getUser());
    }


    @PostMapping("/comment/{postId}/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void reCommentSave(@PathVariable("postId") Long postId,
                              @PathVariable("commentId") Long commentId,
                              @RequestBody CommentSaveDto commentSaveDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.saveReComment(postId, commentId, commentSaveDto, userDetails.getUser());
    }


    @PutMapping("/comment/{commentId}")
    public void update(@PathVariable("commentId") Long commentId,
                       @RequestBody CommentUpdateDto commentUpdateDto,
                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.update(commentId, commentUpdateDto, userDetails.getUser());
    }


    @DeleteMapping("/comment/{commentId}")
    public void delete(@PathVariable("commentId") Long commentId,
                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.delete(commentId, userDetails.getUser());
    }
}
