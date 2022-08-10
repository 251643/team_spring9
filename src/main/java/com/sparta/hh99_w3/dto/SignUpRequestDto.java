package com.sparta.hh99_w3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SignUpRequestDto {

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9].{3,12}$",
            message = "아이디를 4 ~ 12자 내외로 입력해주세요.")
    private String username;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{1,8}$",
            message = "닉네임은 특수문자를 제외한 2~8자리여야 합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9].{5,16}$",
            message = "비밀번호를 6 ~ 16자 내외로 입력해주세요!")
    private String password;

}
