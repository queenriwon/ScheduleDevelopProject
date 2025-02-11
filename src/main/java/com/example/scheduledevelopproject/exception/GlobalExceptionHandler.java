package com.example.scheduledevelopproject.exception;

import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.exception.custom.*;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private ApiResponseDto<String> handleException(BaseException ex) {
        log.error("예외 발생 = {}", ex.getMessage());
        return ApiResponseDto.fail(ex);
    }

    @ExceptionHandler(BaseException.class)
    protected ApiResponseDto<String> unauthorizedScheduleAccessExceptionHandler(BaseException ex) {
        return handleException(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseDto<String> handleValidationExceptionHandler(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("입력값이 올바르지 않습니다.");

        return ApiResponseDto.fail(HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    protected ApiResponseDto<String> handleGeneralException(Exception ex) {
        log.error("예외 발생 = {}", ex);
        return handleException((BaseException) ex);
    }
}
