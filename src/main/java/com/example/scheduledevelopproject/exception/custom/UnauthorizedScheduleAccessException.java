package com.example.scheduledevelopproject.exception.custom;

public class UnauthorizedScheduleAccessException extends RuntimeException {
    public UnauthorizedScheduleAccessException(String message) {
        super(message);
    }
}
