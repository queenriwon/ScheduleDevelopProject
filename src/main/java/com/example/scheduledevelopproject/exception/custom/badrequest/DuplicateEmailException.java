package com.example.scheduledevelopproject.exception.custom.badrequest;

import com.example.scheduledevelopproject.exception.custom.BadRequestException;

public class DuplicateEmailException extends BadRequestException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
