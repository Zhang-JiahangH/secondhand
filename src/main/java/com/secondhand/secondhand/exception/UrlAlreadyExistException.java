package com.secondhand.secondhand.exception;

public class UrlAlreadyExistException extends RuntimeException {
    public UrlAlreadyExistException(String message) {
        super(message);
    }
}