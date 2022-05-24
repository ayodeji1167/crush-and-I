package com.example.crushandi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoveAppException.class)
    public ResponseEntity<?> nameAlreadyExistHandler(LoveAppException appUserException) {
        ErrorMessage errorDetails = new ErrorMessage
                (appUserException.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
