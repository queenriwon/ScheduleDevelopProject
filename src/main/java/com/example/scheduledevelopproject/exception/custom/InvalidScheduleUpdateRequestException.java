package com.example.scheduledevelopproject.exception.custom;

public class InvalidScheduleUpdateRequestException extends RuntimeException {
    public InvalidScheduleUpdateRequestException(String message) {
        super(message);
    }
}
