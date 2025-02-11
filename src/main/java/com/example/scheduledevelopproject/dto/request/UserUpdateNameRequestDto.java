package com.example.scheduledevelopproject.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
    “name”: “nameValue”
 */
@Getter
@AllArgsConstructor
public class UserUpdateNameRequestDto {

    @NotBlank(message = "이름은 필수 입력값 입니다.")
    @Size(max = 8, message = "유저 이름은 8자까지 작성할 수 있습니다.")
    private final String name;
}
