package com.sparta.hh99_w3.service;

import com.sparta.hh99_w3.config.UserDetailsImpl;
import com.sparta.hh99_w3.models.Heart;
import com.sparta.hh99_w3.models.Post;
import com.sparta.hh99_w3.models.Users;
import com.sparta.hh99_w3.repository.HeartRepository;
import com.sparta.hh99_w3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeartService {

    public final HeartRepository heartRepository;
    public final PostRepository postRepository;

    //좋아요
    @Transactional
    public void addLike(Long postId, Users user) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 포스트입니다."));
//        System.out.println(user.getId());
        Heart heart = heartRepository.findByPostAndUser(post, user).orElse(null);

        //존재하면 삭제, 존재하지 않으면 추가
        if (heart == null) {
            heartRepository.save(new Heart(post, user));
        } else {
            heartRepository.delete(heart);
        }
    }
}
