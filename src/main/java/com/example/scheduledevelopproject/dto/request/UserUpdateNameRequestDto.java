package com.example.scheduledevelopproject.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
    “name”: “nameValue”,
    “password”: “passwordValue”
 */
@Getter
@AllArgsConstructor
public class UserUpdateNameRequestDto {

    @NotBlank(message = "이름은 필수 입력값 입니다.")
    private final String name;

    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    private final String password;
}
