package com.example.scheduledevelopproject.exception.custom.forbidden;

import com.example.scheduledevelopproject.exception.custom.ForbiddenException;

public class ForbiddenUserAccessException extends ForbiddenException {
    public ForbiddenUserAccessException(String message) {
        super(message);
    }
}
