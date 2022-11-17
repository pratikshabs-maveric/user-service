package com.maveric.userservice.exceptionhandler;

public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
