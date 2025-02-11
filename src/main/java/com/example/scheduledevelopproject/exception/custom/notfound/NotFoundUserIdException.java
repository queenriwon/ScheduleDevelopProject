package com.example.scheduledevelopproject.exception.custom.notfound;

import com.example.scheduledevelopproject.exception.custom.NotFoundException;

public class NotFoundUserIdException extends NotFoundException {
    public NotFoundUserIdException(String message) {
        super(message);
    }
}
