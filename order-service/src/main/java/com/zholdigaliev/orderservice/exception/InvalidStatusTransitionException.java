package com.zholdigaliev.orderservice.exception;

public class InvalidStatusTransitionException extends RuntimeException{
    public InvalidStatusTransitionException(String message) {
        super(message);
    }
}
