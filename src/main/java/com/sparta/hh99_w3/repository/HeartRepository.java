package com.sparta.hh99_w3.repository;

import com.sparta.hh99_w3.models.Heart;
import com.sparta.hh99_w3.models.Post;
import com.sparta.hh99_w3.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findByPostAndUser(Post post, Users user);

    void deleteByPostAndUser(Post post, Users user);
}
