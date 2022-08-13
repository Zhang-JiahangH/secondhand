package com.secondhand.secondhand.exception;

public class OrderNotExistException extends RuntimeException{
    public OrderNotExistException(String message) {
        super(message);
    }
}
