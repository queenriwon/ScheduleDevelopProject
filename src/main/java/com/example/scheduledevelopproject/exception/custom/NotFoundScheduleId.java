package com.example.scheduledevelopproject.exception.custom;

public class NotFoundScheduleId extends RuntimeException {
    public NotFoundScheduleId(String message) {
        super(message);
    }
}
