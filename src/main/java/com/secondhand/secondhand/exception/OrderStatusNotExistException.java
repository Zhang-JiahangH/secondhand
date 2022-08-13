package com.secondhand.secondhand.exception;

public class OrderStatusNotExistException extends RuntimeException{
    public OrderStatusNotExistException(String message) {
        super(message);
    }
}
