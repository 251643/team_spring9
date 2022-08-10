package com.sparta.hh99_w3.dto;

import lombok.*;

import java.util.List;

@Setter
@AllArgsConstructor
@Getter
public class PostRequestDto {

    private String title;
    private String contents;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PostDto{
        private Long postId;
        private Long userId;
        private String nickname;
        private String content;
        private String image;
        private String createdAt;
        private List<HeartDto> heartDtoList;
    }


}
