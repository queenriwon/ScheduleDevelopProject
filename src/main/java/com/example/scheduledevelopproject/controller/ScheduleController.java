package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.annotation.LoginRequired;
import com.example.scheduledevelopproject.annotation.SessionUser;
import com.example.scheduledevelopproject.dto.request.ScheduleCreateRequestDto;
import com.example.scheduledevelopproject.dto.request.ScheduleUpdateRequestDto;
import com.example.scheduledevelopproject.dto.SessionUserDto;
import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.dto.response.PageResponseDto;
import com.example.scheduledevelopproject.dto.response.ScheduleResponseDto;
import com.example.scheduledevelopproject.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @LoginRequired
    @PostMapping
    public ApiResponseDto<ScheduleResponseDto> createSchedule(
            @Valid @RequestBody ScheduleCreateRequestDto dto,
            @SessionUser SessionUserDto userSession
            ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.createSchedule(userSession.getId(), dto);
        return ApiResponseDto.OK(scheduleResponseDto, "일정 작성 성공");
    }

    @GetMapping
    public ApiResponseDto<PageResponseDto<ScheduleResponseDto>> findAllSchedule(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponseDto<ScheduleResponseDto> allSchedule = scheduleService.findAllSchedule(page, size);
        return ApiResponseDto.OK(allSchedule, "일정 전체 조회 성공");
    }

    @GetMapping("/{id}")
    public ApiResponseDto<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findScheduleById(id);
        return ApiResponseDto.OK(scheduleResponseDto, "id " + id + " 일정 조회 성공");
    }

    @LoginRequired
    @PatchMapping("/{id}")
    public ApiResponseDto<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleUpdateRequestDto dto,
            @SessionUser SessionUserDto userSession
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, userSession.getId(), dto);
        return ApiResponseDto.OK(scheduleResponseDto, "id " + id + " 일정 수정 성공");
    }

    @LoginRequired
    @DeleteMapping("/{id}")
    public ApiResponseDto<Void> deleteSchedule(
            @PathVariable Long id,
            @SessionUser SessionUserDto userSession
    ) {
        scheduleService.deleteSchedule(id, userSession.getId());
        return ApiResponseDto.OK("id " + id + " 일정 삭제");
    }
}
