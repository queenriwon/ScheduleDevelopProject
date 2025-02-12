package com.example.scheduledevelopproject.exception.custom;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HandledException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
