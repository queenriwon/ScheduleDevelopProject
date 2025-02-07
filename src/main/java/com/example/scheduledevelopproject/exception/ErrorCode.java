package com.example.scheduledevelopproject.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    FAIL(500, "서버 내부 에러");

    private final int errorCode;
    private final String errorDetails;

    ErrorCode(int errorCode, String errorDetails) {
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }
}
