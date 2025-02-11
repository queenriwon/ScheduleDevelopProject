package com.example.scheduledevelopproject.exception;

import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.exception.custom.*;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(UnauthorizedException.class)
    protected ApiResponseDto<String> unauthorizedScheduleAccessExceptionHandler(UnauthorizedException ex) {
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

//    @ExceptionHandler(NoMatchPasswordConfirmation.class)
//    protected ApiResponseDto<String> noMatchPasswordConfirmationHandler(NoMatchPasswordConfirmation ex) {
//        log.error("예외 발생 = {}", ex.getMessage());
//        return ApiResponseDto.fail(ErrorCode.NO_MATCH_PASSWORD_CONFIRMATION);
//    }
//
//    @ExceptionHandler(InvalidScheduleUpdateRequestException.class)
//    protected ApiResponseDto<String> invalidScheduleUpdateRequestException(InvalidScheduleUpdateRequestException ex) {
//        log.error("예외 발생 = {}", ex.getMessage());
//        return ApiResponseDto.fail(ErrorCode.MISSING_REQUIRED_FIELD);
//    }
//
//    @ExceptionHandler({NotFoundScheduleId.class, NotFoundUserId.class, NotFoundCommentId.class})
//    protected ApiResponseDto<String> idNotFoundExceptionHandler(RuntimeException ex) {
//        log.error("예외 발생 = {}", ex.getMessage());
//        return ApiResponseDto.fail(ErrorCode.NOT_FOUND_ID);
//    }
//
//    @ExceptionHandler(PasswordMismatchException.class)
//    protected ApiResponseDto<String> passwordMismatchExceptionHandler(PasswordMismatchException ex) {
//        log.error("예외 발생 = {}", ex.getMessage());
//        return ApiResponseDto.fail(ErrorCode.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(NotFoundUserByEmail.class)
//    protected ApiResponseDto<String> notFoundUserByEmailExceptionHandler(RuntimeException ex) {
//        log.error("예외 발생 = {}", ex.getMessage());
//        return ApiResponseDto.fail(ErrorCode.LOGIN_UNAUTHORIZED);
//    }
}
