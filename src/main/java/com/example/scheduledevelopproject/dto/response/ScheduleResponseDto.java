package com.example.scheduledevelopproject.dto.response;

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
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}