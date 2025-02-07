package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.dto.request.ScheduleCreateRequestDto;
import com.example.scheduledevelopproject.dto.request.ScheduleUpdateRequestDto;
import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.dto.response.ScheduleResponseDto;
import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/post")
    public ApiResponseDto<ScheduleResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto dto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.createSchedule(dto);
        return ApiResponseDto.OK(scheduleResponseDto, "일정 작성 성공");
    }

    @GetMapping
    public ApiResponseDto<List<ScheduleResponseDto>> findAllSchedule() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAllSchedule();
        return ApiResponseDto.OK(scheduleResponseDtoList, "일정 전체 조회 성공");
    }

    @GetMapping("/{id}")
    public ApiResponseDto<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findScheduleById(id);
        return ApiResponseDto.OK(scheduleResponseDto, "id " + id + " 일정 조회 성공");
    }

    @PatchMapping("/{id}")
    public ApiResponseDto<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto dto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, dto);
        return ApiResponseDto.OK(scheduleResponseDto, "id " + id + " 일정 수정 성공");
    }

    @PostMapping("/{id}/delete")
    public ApiResponseDto<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        scheduleService.deleteSchedule(id, request.get("password"));
        return ApiResponseDto.OK("id " + id + " 일정 삭제");
    }

}
