package com.sparta.hh99_w3.repository;

import com.sparta.hh99_w3.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername(String username);
    Optional<Users> findByNickname(String nickname);

}
