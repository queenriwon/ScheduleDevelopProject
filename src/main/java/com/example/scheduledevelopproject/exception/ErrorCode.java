package com.example.scheduledevelopproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    FAIL(500, "서버 내부 에러"),
    NO_MATCH_PASSWORD_CONFIRMATION(400, "유저 비밀번호 확인 불일치"),
    MISSING_REQUIRED_FIELD(400, "필요한 필드를 받지 못함"),
    UNAUTHORIZED(401, "비밀번호 불일치"),
    NOT_FOUND_ID(404, "존재하지 않는 id값");

    private final int errorCode;
    private final String errorDetails;
}
