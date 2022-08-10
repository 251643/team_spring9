package com.sparta.hh99_w3.exception.comment;


import com.sparta.hh99_w3.exception.BaseException;
import com.sparta.hh99_w3.exception.BaseExceptionType;

public class CommentException extends BaseException {

    private BaseExceptionType baseExceptionType;

    public CommentException(CommentExceptionType notPoundComment) {
        super();
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return this.baseExceptionType;
    }
}