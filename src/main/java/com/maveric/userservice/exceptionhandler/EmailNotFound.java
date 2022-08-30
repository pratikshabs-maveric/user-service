package com.maveric.userservice.exceptionhandler;

public class EmailNotFound extends RuntimeException {
    public EmailNotFound(String message){
        super(message);
    }
}
