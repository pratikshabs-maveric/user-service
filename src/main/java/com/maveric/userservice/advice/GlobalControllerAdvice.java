package com.maveric.userservice.advice;

import com.maveric.userservice.constant.ErrorAndSuccessMessageConstants;
import com.maveric.userservice.dto.Error;
import com.maveric.userservice.exception.EmailDuplicationException;
import com.maveric.userservice.exception.UserIdMismatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.maveric.userservice.exception.UserNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> internalServerError(Exception exception){
        Error error = getError(String.valueOf(exception.getMessage()),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error>  handleNullInput(MethodArgumentNotValidException methodArgumentNotValidException){
        Error error = getError(methodArgumentNotValidException.getBindingResult().getAllErrors().get(0).getDefaultMessage(),String.valueOf(HttpStatus.BAD_REQUEST));
        logger.error(methodArgumentNotValidException.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error>  handleUserNotFoundException(UserNotFoundException userNotFoundException){
        Error error = getError(userNotFoundException.getMessage(),String.valueOf(HttpStatus.NOT_FOUND.value()));
        logger.error(userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EmailDuplicationException.class)
    public ResponseEntity<Error>  handleEmailDuplication(EmailDuplicationException e){
        Error error = getError(e.getMessage(),String.valueOf(HttpStatus.BAD_REQUEST.value()));
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserIdMismatch.class)
    public ResponseEntity<Error>  handleUserMismatch(UserIdMismatch userIdMismatch){
        Error error = getError(userIdMismatch.getMessage(),String.valueOf(HttpStatus.BAD_REQUEST.value()));
        logger.error(userIdMismatch.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleFormatException(HttpMessageNotReadableException httpMessageNotReadableException){
        Error error = getError(ErrorAndSuccessMessageConstants.GENDER_ERROR,String.valueOf(HttpStatus.BAD_REQUEST));
        logger.error(ErrorAndSuccessMessageConstants.GENDER_ERROR);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpServerErrorException.ServiceUnavailable.class)
    public ResponseEntity<Error>  serviceUnavailable(HttpServerErrorException.ServiceUnavailable serviceUnavailable){
        Error error = getError(String.valueOf(serviceUnavailable.getMessage()),String.valueOf(HttpStatus.SERVICE_UNAVAILABLE));
        logger.error(serviceUnavailable.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error>  noHandlerException(NoHandlerFoundException noHandlerFoundException){
        Error error = getError(String.valueOf(noHandlerFoundException.getMessage()),String.valueOf(HttpStatus.NOT_FOUND));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    private Error getError(String message , String code){
        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        return error;

    }
}
