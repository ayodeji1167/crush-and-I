package com.example.crushandi.exception;

public class LoveAppException extends RuntimeException {
    private String message;

    public LoveAppException(String message) {
        super(message);
    }
}
