package com.sparta.hh99_w3.controller;

import com.sparta.hh99_w3.config.jwt.JwtTokenProvider;
import com.sparta.hh99_w3.dto.LoginRequestDto;
import com.sparta.hh99_w3.dto.SignUpRequestDto;
import com.sparta.hh99_w3.models.Users;
import com.sparta.hh99_w3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;


    //회원가입
    @PostMapping("/api/user/signup")
    public Map<String, Object> userRegister(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {

        Users user = userService.register(signUpRequestDto);

        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("result", "success");
            response.put("message", "회원가입 성공");
        } else {
            response.put("result", "false");
            response.put("message", "회원가입 실패");
        }

        return response;
    }

    //로그인
    @PostMapping("/api/user/login")
    public Map<String, Object> userLogin(@RequestBody LoginRequestDto loginRequestDto) {

        Users user = userService.userLoginCheck(loginRequestDto);

        Map<String, Object> response = new HashMap<>();


        response.put("token", tokenProvider.createToken(Long.toString(user.getId()), user.getUsername(), user.getNickname()));
        response.put("userPk", user.getId());
        response.put("message", "로그인 성공");


        return response;

    }
}
