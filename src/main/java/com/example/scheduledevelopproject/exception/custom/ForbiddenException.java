package com.example.scheduledevelopproject.exception.custom;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends HandledException {
    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
