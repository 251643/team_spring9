package com.sparta.hh99_w3.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.hh99_w3.dto.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // (nullable = false)을 붙이면 에러 발생
    @Column
    private String title;

    @Column
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Users users;

    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    @Builder.Default
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Heart> heartList = new ArrayList<>();


    public Post(PostRequestDto postRequestDto, Users users) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
        this.users = users;
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }


    public void addComment(Comment comment){
        System.out.println(comment.getContent());
        comments.add(comment);
    }

    public void confirmUser(Users users) {
        this.users = users;
        users.addPost(this);
    }

}
