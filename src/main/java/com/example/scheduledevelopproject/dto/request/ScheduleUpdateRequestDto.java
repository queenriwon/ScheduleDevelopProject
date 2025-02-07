package com.example.scheduledevelopproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
    “todoTitle”: “todoTitleValue”,
    “todoContents”: “todoContentsValue”,
    "password": "passwordValue"
 */
@Getter
@AllArgsConstructor
public class ScheduleUpdateRequestDto {

    @Size(max = 20, message = "일정 제목은 20자까지 작성할 수 있습니다.")
    private final String todoTitle;
    private final String todoContents;

    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    private final String password;

}
