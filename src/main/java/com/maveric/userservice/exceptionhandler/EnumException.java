package com.maveric.userservice.exceptionhandler;

public class EnumException extends Exception {

    enum Gender {
        MALE, FEMALE;
    }


    public EnumException(String message) {
        super(message);
    }
}
