package com.example.scheduledevelopproject.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Size(max = 8, message = "유저 이름은 8자까지 작성할 수 있습니다.")
    private final String name;

    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$",
            message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력값 입니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    private final String password;
    private final String passwordCheck;
}
