package com.example.scheduledevelopproject.exception.custom.unauthorized;

import com.example.scheduledevelopproject.exception.custom.UnauthorizedException;

public class UnauthorizedAccessException extends UnauthorizedException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}