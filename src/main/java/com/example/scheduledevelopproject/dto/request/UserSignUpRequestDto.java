package com.example.scheduledevelopproject.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
    “name”: “nameValue”,
    “email”: “email@email.com”,
    “password”: “passwordValue”,
    “passwordCheck”: “passwordValue”
 */
@Getter
@AllArgsConstructor
public class UserSignUpRequestDto {

    @NotBlank(message = "이름은 필수 입력값 입니다.")
    private final String name;

    // TODO: 정규표현식 알아보기
    @Email
    @NotBlank(message = "이메일은 필수 입력값 입니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    private final String password;
    private final String passwordCheck;


}
