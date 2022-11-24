package com.maveric.userservice.exception;

public class UserIdMismatch extends RuntimeException{
    public UserIdMismatch(String message){
        super(message);
    }
}

