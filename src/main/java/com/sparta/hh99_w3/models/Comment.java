package com.sparta.hh99_w3.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isRemoved= false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Comment parent;

    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    public static Comment createComment(String content, Comment parent) {
        Comment comment = new Comment();
        comment.content = content;
        comment.parent = parent;
        comment.isRemoved = false;
        return comment;
    }

    //== 연관관계 편의 메서드 ==//
    public void confirmUser(Users users) {
        this.users = users;
        users.addComment(this);
    }

    public void confirmPost(Post post) {
        this.post = post;
        post.addComment(this);
    }

    public void confirmParent(Comment parent){
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(Comment child){
        children.add(child);
    }

    public void updateContent(String content) {
        this.content = content;
    }
    public void remove() {
        this.isRemoved = true;
    }


    //== 비즈니스 로직 ==//
    public List<Comment> findRemovableList() {

        List<Comment> result = new ArrayList<>();

        Optional.ofNullable(this.parent).ifPresentOrElse(

                parentComment ->{//대댓글인 경우 (부모가 존재하는 경우)
                    if( parentComment.isRemoved()&& parentComment.isAllChildRemoved()){
                        result.addAll(parentComment.getChildren());
                        result.add(parentComment);
                    }
                },

                () -> {//댓글인 경우
                    if (isAllChildRemoved()) {
                        result.add(this);
                        result.addAll(this.getChildren());
                    }
                }
        );

        return result;
    }


    //모든 자식 댓글이 삭제되었는지 판단
    private boolean isAllChildRemoved() {
        return getChildren().stream()//https://kim-jong-hyun.tistory.com/110 킹종현님 사랑합니다.
                .map(Comment::isRemoved)//지워졌는지 여부로 바꾼다
                .filter(isRemove -> !isRemove)//지워졌으면 true, 안지워졌으면 false이다. 따라서 filter에 걸러지는 것은 false인 녀석들이고, 있다면 false를 없다면 orElse를 통해 true를 반환한다.
                .findAny()//지워지지 않은게 하나라도 있다면 false를 반환
                .orElse(true);//모두 지워졌다면 true를 반환

    }
}