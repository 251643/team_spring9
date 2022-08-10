package com.sparta.hh99_w3.service;


import com.sparta.hh99_w3.dto.HeartDto;
import com.sparta.hh99_w3.dto.PostRequestDto;
import com.sparta.hh99_w3.exception.comment.CommentException;
import com.sparta.hh99_w3.exception.comment.CommentExceptionType;
import com.sparta.hh99_w3.models.Heart;
import com.sparta.hh99_w3.models.Post;
import com.sparta.hh99_w3.models.Users;
import com.sparta.hh99_w3.repository.PostRepository;
import com.sparta.hh99_w3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 게시글 작성 기능
    public void createPost(PostRequestDto requestDto, Users users) {
        Post post = new Post(requestDto, users);

        postRepository.save(post);
    }

    // 게시글 조회 기능
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    // 게시글 상세 조회 기능
    public Post getEachPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 포스팅이 존재하지 않습니다")
        );

//        post.confirmUser(userRepository.findByUsername(SecurityUtil.getLoginUsername()).orElseThrow(() ->
//                new UserException(UserExceptionType.NOT_FOUND_MEMBER)));

        return post;
    }

    @Transactional // 업데이트시 db에 반영되도록 해줌
    public Long update(Long id, PostRequestDto requestDto, Users users) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        if(!post.getUsers().getUsername().equals(users.getUsername())){
            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_UPDATE_COMMENT);
        }

        post.update(requestDto);
        return post.getId();
    }

    // 게시글 삭제 기능
    public Long deletePost(Long id, Users users) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        if(!post.getUsers().getUsername().equals(users.getUsername())){
            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_UPDATE_COMMENT);
        }
        postRepository.deleteById(id);
        return id;
    }

    private void setHeartResponseDto(List<Heart> heartList, PostRequestDto.PostDto postDto) {
        for (Heart heart : heartList) {
            HeartDto heartDto = new HeartDto(heart.getUser().getId());
            postDto.getHeartDtoList().add(heartDto);
        }
    }

}
