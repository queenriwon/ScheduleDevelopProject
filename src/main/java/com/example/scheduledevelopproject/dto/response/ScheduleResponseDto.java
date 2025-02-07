package com.example.scheduledevelopproject.dto.response;

import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.entity.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String name;
    private String todoTitle;
    private String todoContents;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedules schedules) {
        this.id = schedules.getId();
        this.name = schedules.getUsers().getName();
        this.todoTitle = schedules.getTodoTitle();
        this.todoContents = schedules.getTodoContents();
        this.createdAt = schedules.getCreatedAt();
        this.modifiedAt = schedules.getModifiedAt();
    }
}