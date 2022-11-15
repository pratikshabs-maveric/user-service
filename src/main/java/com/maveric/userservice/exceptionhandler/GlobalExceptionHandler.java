package com.maveric.userservice.exceptionhandler;

import com.maveric.userservice.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.*;
import static com.maveric.userservice.constants.Constants.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExist.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleUEmailAlreadyExistException(EmailAlreadyExist exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(EMAIL_ALREADY_EXIST);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public final ErrorDto handleValidationsExceptions(MethodArgumentNotValidException exception) {
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setCode(BAD_REQUEST_CODE);
//        errorDto.setMessage(BAD_REQUEST_MESSAGE);
//        return errorDto;
//    }

    // Firstname, lastname, empty fields handled
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDto handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        ErrorDto errorDto = new ErrorDto();
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        errorDto.setCode(BAD_REQUEST_CODE);
//        errorDto.setMessage(BAD_REQUEST_MESSAGE);
//        errorDto.setErrors(errors);
//        return errorDto;
//    }

    // user not found
    @ExceptionHandler(UserNotExist.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleUserNotFoundException(UserNotExist exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(USER_NOT_FOUND_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }

    @ExceptionHandler(EmailNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleUserNotFoundException(EmailNotFound exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(EMAIL_NOT_FOUND_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }

    @ExceptionHandler(EnumException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public final ErrorDto handleEnumException(EnumException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(ENUM_INVALID);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }
}
