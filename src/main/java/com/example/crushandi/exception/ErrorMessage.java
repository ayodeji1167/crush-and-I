package com.example.crushandi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {
    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime localDateTime;

}
