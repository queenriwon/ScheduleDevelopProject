package com.example.scheduledevelopproject.exception.custom.unauthorized;

import com.example.scheduledevelopproject.exception.custom.UnauthorizedException;

public class UnauthorizedUserPasswordException extends UnauthorizedException {
    public UnauthorizedUserPasswordException(String message) {
        super(message);
    }
}
