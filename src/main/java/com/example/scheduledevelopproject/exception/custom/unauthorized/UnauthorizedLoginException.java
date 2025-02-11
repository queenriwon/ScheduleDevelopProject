package com.example.scheduledevelopproject.exception.custom.unauthorized;

import com.example.scheduledevelopproject.exception.custom.UnauthorizedException;

public class UnauthorizedLoginException extends UnauthorizedException {
    public UnauthorizedLoginException(String message) {
        super(message);
    }
}
