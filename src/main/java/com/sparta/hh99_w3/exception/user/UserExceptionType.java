package com.sparta.hh99_w3.exception.user;

import com.sparta.hh99_w3.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum UserExceptionType implements BaseExceptionType {


    ALREADY_EXIST_USERNAME(600, HttpStatus.CONFLICT,"이미 존재하는 아이디입니다"),
    ALREADY_EXIST_NICKNAME(601, HttpStatus.CONFLICT,"이미 존재하는 닉네임입니다."),
    WRONG_PASSWORD(602,HttpStatus.BAD_REQUEST, "비밀번호가 잘못되었습니다."),
    NOT_FOUND_MEMBER(603, HttpStatus.NOT_FOUND, "회원 정보가 없습니다."),
    LOGIN_TOKEN_EXPIRE(604, HttpStatus.NOT_FOUND, "토큰이 존재하지 않습니다.");


    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;


    //
    UserExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }


    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }


}
