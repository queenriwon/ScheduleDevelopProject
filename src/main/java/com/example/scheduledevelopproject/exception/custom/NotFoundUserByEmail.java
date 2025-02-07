package com.example.scheduledevelopproject.exception.custom;

public class NotFoundUserByEmail extends RuntimeException {
    public NotFoundUserByEmail(String message) {
        super(message);
    }
}
