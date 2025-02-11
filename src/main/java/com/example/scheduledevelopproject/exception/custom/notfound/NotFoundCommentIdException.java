package com.example.scheduledevelopproject.exception.custom.notfound;

import com.example.scheduledevelopproject.exception.custom.NotFoundException;

public class NotFoundCommentIdException extends NotFoundException {
    public NotFoundCommentIdException(String message) {
        super(message);
    }
}
