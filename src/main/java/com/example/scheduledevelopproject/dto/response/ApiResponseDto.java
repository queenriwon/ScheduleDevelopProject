package com.example.scheduledevelopproject.dto.response;

import com.example.scheduledevelopproject.exception.custom.HandledException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResponseDto<T> {
    private int statusCode;
    private String message;
    private T data;
    private String errorDetails;

    public ApiResponseDto(HttpStatus statusCode, String message, T data) {
        this.statusCode = statusCode.value();
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponseDto<T> OK(T data, String message) {
        return new ApiResponseDto<>(HttpStatus.OK, message, data);
    }

    public static <T> ApiResponseDto<T> OK(String message) {
        return new ApiResponseDto<>(HttpStatus.OK.value(), message, null, null);
    }

    public static <T> ApiResponseDto<T> fail(HandledException ex) {
        return new ApiResponseDto<>(ex.getHttpStatus().value(),
                ex.getHttpStatus().name(),
                null,
                ex.getMessage());
    }

    public static <T> ApiResponseDto<T> fail(HttpStatus httpStatus, String errorDetails) {
        return new ApiResponseDto<>(httpStatus.value(),
                httpStatus.name(),
                null,
                errorDetails);
    }
}
