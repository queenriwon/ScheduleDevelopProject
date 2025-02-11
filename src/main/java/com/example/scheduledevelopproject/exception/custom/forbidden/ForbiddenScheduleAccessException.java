package com.example.scheduledevelopproject.exception.custom.forbidden;

import com.example.scheduledevelopproject.exception.custom.ForbiddenException;

public class ForbiddenScheduleAccessException extends ForbiddenException {
    public ForbiddenScheduleAccessException(String message) {
        super(message);
    }
}
