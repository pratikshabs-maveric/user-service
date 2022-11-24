package com.maveric.userservice.exception;

import com.maveric.userservice.advice.GlobalControllerAdvice;
import com.maveric.userservice.dto.Error;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserExceptionTest {

    @InjectMocks
    private GlobalControllerAdvice globalControllerAdvice;

    @Test
    void handleUserNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException("User Not found");
        ResponseEntity<Error> error = globalControllerAdvice.handleUserNotFoundException(exception);
        assertEquals("404",error.getBody().getCode());
    }

    @Test
    void handleEmailDuplicatedException() {
        EmailDuplicationException exception = new EmailDuplicationException("Email already present");
        ResponseEntity<Error> error = globalControllerAdvice.handleEmailDuplication(exception);
        assertEquals("400",error.getBody().getCode());
    }

    @Test
    void handleUserIdMismatchException() {
        UserIdMismatch exception = new UserIdMismatch("user id mismatch");
        ResponseEntity<Error> error = globalControllerAdvice.handleUserMismatch(exception);
        assertEquals("400",error.getBody().getCode());
    }
}
