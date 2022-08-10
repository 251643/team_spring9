package com.sparta.hh99_w3.dto;

import com.sparta.hh99_w3.models.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class PostResponseDto {

    @Getter
    @NoArgsConstructor
    public static class ListResponseDto{ //static? 바로가지고올수있다?
        private String title;
        private LocalDateTime modifiedAt;


        //public BoardResponseDto(){}

        public ListResponseDto(Post post) {
            this.title = post.getTitle();
           // this.username = post.getUsername();
            this.modifiedAt = post.getModifiedAt();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class DetailResponseDto {
        private String title;
       // private String username;
        private LocalDateTime modifiedAt;
        private String contents;


        //public BoardResponseDto(){}

        public DetailResponseDto(Post post) {
            this.title = post.getTitle();
         //   this.username = post.getUsername();
            this.modifiedAt = post.getModifiedAt();
            this.contents = post.getContents();
        }
    }
}



