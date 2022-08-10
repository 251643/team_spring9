package com.sparta.hh99_w3.service;

import com.sparta.hh99_w3.dto.LoginRequestDto;
import com.sparta.hh99_w3.dto.SignUpRequestDto;
import com.sparta.hh99_w3.exception.user.UserException;
import com.sparta.hh99_w3.exception.user.UserExceptionType;
import com.sparta.hh99_w3.models.Users;
import com.sparta.hh99_w3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    //회원가입
    @Transactional
    public Users register(SignUpRequestDto signUpRequestDto) {

        String username = signUpRequestDto.getUsername();
        String nickname = signUpRequestDto.getNickname();


        // username 중복검사
        if(userRepository.findByUsername(username).isPresent()){
            throw new UserException(UserExceptionType.ALREADY_EXIST_USERNAME);
        }
        //nickname 중복검사
        if(userRepository.findByNickname(nickname).isPresent()){
            throw new UserException(UserExceptionType.ALREADY_EXIST_NICKNAME);

        }

        String password = passwordEncoder.encode(signUpRequestDto.getPassword());

        Users user = Users.builder()
                .username(username)
                .nickname(nickname)
                .password(password)

                .build();

        return userRepository.save(user);

    }

    //로그인 유효성 검사
    public Users userLoginCheck(LoginRequestDto loginRequestDto) {

        Users user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_MEMBER));

        if(!passwordEncoder.matches(loginRequestDto.getPassword(),user.getPassword())){
            throw new UserException(UserExceptionType.WRONG_PASSWORD);
        }


        return user;
    }
}
