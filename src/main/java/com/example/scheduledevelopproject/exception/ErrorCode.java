package com.example.scheduledevelopproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러"),
    NO_MATCH_PASSWORD_CONFIRMATION(HttpStatus.BAD_REQUEST, "유저 비밀번호 확인 불일치"),
    MISSING_REQUIRED_FIELD(HttpStatus.BAD_REQUEST, "필요한 필드를 받지 못함"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "비밀번호 불일치"),
    NOT_FOUND_ID(HttpStatus.NOT_FOUND, "존재하지 않는 id값"),
    LOGIN_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "로그인 실패"),;

    private final HttpStatus httpStatus;
    private final String errorDetails;
}
