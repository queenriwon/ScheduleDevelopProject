package com.example.scheduledevelopproject.exception.custom;

import com.example.scheduledevelopproject.exception.custom.notfound.NotFoundCommentIdException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HandledException extends RuntimeException {
    private final HttpStatus httpStatus;

    public HandledException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}