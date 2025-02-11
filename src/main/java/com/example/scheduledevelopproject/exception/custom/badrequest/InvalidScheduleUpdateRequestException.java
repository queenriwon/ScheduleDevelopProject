package com.example.scheduledevelopproject.exception.custom.badrequest;

import com.example.scheduledevelopproject.exception.custom.BadRequestException;

public class InvalidScheduleUpdateRequestException extends BadRequestException {
    public InvalidScheduleUpdateRequestException(String message) {
        super(message);
    }
}
