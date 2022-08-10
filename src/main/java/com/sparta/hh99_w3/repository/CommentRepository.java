package com.sparta.hh99_w3.repository;

import com.sparta.hh99_w3.models.Comment;
import com.sparta.hh99_w3.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
    Optional<Comment> findById(Long id);
}
