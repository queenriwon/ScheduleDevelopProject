package com.example.scheduledevelopproject.exception.custom.forbidden;

import com.example.scheduledevelopproject.exception.custom.ForbiddenException;

public class ForbiddenCommentAccessException extends ForbiddenException {
    public ForbiddenCommentAccessException(String message) {
        super(message);
    }
}

