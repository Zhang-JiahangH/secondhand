package com.secondhand.secondhand.exception;

public class UserNotMatchException extends RuntimeException {
    public UserNotMatchException(String message) {
        super(message);
    }
}