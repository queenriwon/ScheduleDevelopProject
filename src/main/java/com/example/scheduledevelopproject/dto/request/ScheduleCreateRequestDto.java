package com.example.scheduledevelopproject.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
    "userId": 1,
    “todoTitle”: “todoTitleValue”,
    “todoContents”: “todoContentsValue”,
    "password": "passwordValue"
 */
@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto {

    @NotBlank(message = "유져 ID는 필수 입력값 입니다.")
    private final Long userId;

    @Size(max = 200, message = "일정 제목은 200자까지 작성할 수 있습니다.")
    @NotBlank(message = "일정 제목은 필수 입력값 입니다.")
    private final String todoTitle;

    @NotBlank(message = "일정 내용은 필수 입력값 입니다.")
    private final String todoContents;

    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    private final String password;

}
