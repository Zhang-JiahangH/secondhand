package com.secondhand.secondhand.exception;

public class CityNotExistException extends RuntimeException {
    public CityNotExistException(String message) {
        super(message);
    }
}
