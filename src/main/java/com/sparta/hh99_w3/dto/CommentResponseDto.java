package com.sparta.hh99_w3.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

//    private Long id;
//    private String content;
//    private String createdDate;
//    private String modifiedDate;
//    private String nickname;
//    private Long postsId;     /* Entity -> Dto*/
//
//    public CommentResponseDto(Comment comment) {
//        this.id = comment.getId();
//        this.content = comment.getContent();
//        this.createdDate = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//        this.modifiedDate = comment.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
//        this.nickname = comment.getUser().getUsername();
//        this.postsId = comment.getPost().getId();
//    }
}
