package com.secondhand.secondhand.exception;

public class AddressOfThisUserAlreadyExistException extends RuntimeException{
    public AddressOfThisUserAlreadyExistException(String message) {
        super(message);
    }
}
