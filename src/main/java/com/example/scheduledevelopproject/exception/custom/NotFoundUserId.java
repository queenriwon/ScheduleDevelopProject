package com.example.scheduledevelopproject.exception.custom;

public class NotFoundUserId extends RuntimeException {
    public NotFoundUserId(String message) {
        super(message);
    }
}
