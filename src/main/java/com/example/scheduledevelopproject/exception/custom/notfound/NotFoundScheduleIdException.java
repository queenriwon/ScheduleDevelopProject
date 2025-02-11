package com.example.scheduledevelopproject.exception.custom.notfound;

import com.example.scheduledevelopproject.exception.custom.NotFoundException;

public class NotFoundScheduleIdException extends NotFoundException {
    public NotFoundScheduleIdException(String message) {
        super(message);
    }
}
