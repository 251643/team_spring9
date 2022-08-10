package com.sparta.hh99_w3.dto;


import com.sparta.hh99_w3.models.Comment;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveDto{

    private Long id;
    private String content;

    public Comment toEntity() {
        return Comment.builder().id(id).content(content).build();
    }
}