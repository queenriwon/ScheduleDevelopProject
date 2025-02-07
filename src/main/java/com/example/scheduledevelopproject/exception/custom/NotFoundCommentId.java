package com.example.scheduledevelopproject.exception.custom;

public class NotFoundCommentId extends RuntimeException {
    public NotFoundCommentId(String message) {
        super(message);
    }
}
