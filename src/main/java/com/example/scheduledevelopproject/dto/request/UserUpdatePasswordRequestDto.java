package com.example.scheduledevelopproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
    “oldPassword”: “passwordValue”,
    “newPassword”: “newPassword”,
    “newPasswordCheck”: “newPassword”
 */

@Getter
@AllArgsConstructor
public class UserUpdatePasswordRequestDto {

    @NotBlank(message = "현재 비밀번호는 필수 입력값 입니다.")
    private final String oldPassword;

    @NotBlank(message = "새 비밀번호는 필수 입력값 입니다.")
    private final String newPassword;
    private final String newPasswordCheck;

}
