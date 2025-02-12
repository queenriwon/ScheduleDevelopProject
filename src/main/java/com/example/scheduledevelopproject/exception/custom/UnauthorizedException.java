package com.example.scheduledevelopproject.exception.custom;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HandledException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
