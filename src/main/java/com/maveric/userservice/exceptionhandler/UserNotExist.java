package com.maveric.userservice.exceptionhandler;

public class UserNotExist extends RuntimeException{
    public UserNotExist(String message) {
        super(message);
    }
}
