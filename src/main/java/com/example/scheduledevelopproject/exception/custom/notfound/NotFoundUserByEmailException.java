package com.example.scheduledevelopproject.exception.custom.notfound;

import com.example.scheduledevelopproject.exception.custom.NotFoundException;

public class NotFoundUserByEmailException extends NotFoundException {
    public NotFoundUserByEmailException(String message) {
        super(message);
    }
}
