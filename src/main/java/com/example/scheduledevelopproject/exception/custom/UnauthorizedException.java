package com.example.scheduledevelopproject.exception.custom;

import com.example.scheduledevelopproject.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
