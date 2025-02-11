package com.example.scheduledevelopproject.exception.custom;

public class UnauthorizedUserAccessException extends UnauthorizedException {
    public UnauthorizedUserAccessException(String message) {
        super(message);
    }
}
