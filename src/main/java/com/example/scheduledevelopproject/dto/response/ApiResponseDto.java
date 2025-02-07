package com.example.scheduledevelopproject.dto.response;

import com.example.scheduledevelopproject.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponseDto<T> {
    private int statusCode;
    private String message;
    private T data;
    private String errorDetails;

    public ApiResponseDto(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponseDto<T> OK(T data, String message) {
        return new ApiResponseDto<>(200, message, data);
    }

    public static <T> ApiResponseDto<T> OK(String message) {
        return new ApiResponseDto<>(200, message, null, null);
    }

    public static <T> ApiResponseDto<T> fail(ErrorCode errorCode) {
        return new ApiResponseDto<>(errorCode.getErrorCode(), errorCode.name(), null, errorCode.getErrorDetails());
    }
}
