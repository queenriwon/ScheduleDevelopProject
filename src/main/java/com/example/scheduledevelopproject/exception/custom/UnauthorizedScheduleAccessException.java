package com.example.scheduledevelopproject.exception.custom;

public class UnauthorizedScheduleAccessException extends UnauthorizedException {
    public UnauthorizedScheduleAccessException(String message) {
        super(message);
    }
}
