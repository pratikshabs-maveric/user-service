package com.maveric.userservice.exception;

public class EmailDuplicationException extends RuntimeException {
    public EmailDuplicationException(String email){
        super("Email id already Exist "+ email);
    }
}
