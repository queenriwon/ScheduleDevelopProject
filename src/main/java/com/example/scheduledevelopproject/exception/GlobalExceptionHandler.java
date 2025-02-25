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

    @ExceptionHandler(HandledException .class)
    protected ApiResponseDto<String> handleHandledException(HandledException ex) {
        log.error("예외 발생 = {}", ex.getMessage());
        return ApiResponseDto.fail(ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseDto<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("입력값이 올바르지 않습니다.");

        log.error("예외 발생 = {}", errorMessage);
        return ApiResponseDto.fail(HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ApiResponseDto<String> handleGeneralException(Exception ex) {
        log.error("예외 발생 = {}", ex);
        return ApiResponseDto.fail(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류");
    }
}
