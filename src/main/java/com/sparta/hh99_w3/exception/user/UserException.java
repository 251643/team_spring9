package com.sparta.hh99_w3.exception.user;

import com.sparta.hh99_w3.exception.BaseException;
import com.sparta.hh99_w3.exception.BaseExceptionType;

public class UserException extends BaseException {
    private BaseExceptionType exceptionType;

    public UserException(BaseExceptionType exceptionType){
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType(){
        return exceptionType;
    }

}
