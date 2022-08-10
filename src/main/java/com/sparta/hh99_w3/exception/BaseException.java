package com.sparta.hh99_w3.exception;


public abstract class BaseException extends RuntimeException{
    public abstract BaseExceptionType getExceptionType();

}