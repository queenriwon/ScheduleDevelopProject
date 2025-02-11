package com.example.scheduledevelopproject.controller;

import com.example.scheduledevelopproject.dto.request.ScheduleCreateRequestDto;
import com.example.scheduledevelopproject.dto.request.ScheduleUpdateRequestDto;
import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.dto.response.PageResponseDto;
import com.example.scheduledevelopproject.dto.response.ScheduleResponseDto;
import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ApiResponseDto<ScheduleResponseDto> createSchedule(
            @Valid @RequestBody ScheduleCreateRequestDto dto,
            HttpServletRequest httpServletRequest
    ) {
        Long userId = getUserIdBySession(httpServletRequest);
        ScheduleResponseDto scheduleResponseDto = scheduleService.createSchedule(userId, dto);
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

    @PatchMapping("/{id}")
    public ApiResponseDto<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleUpdateRequestDto dto,
            HttpServletRequest httpServletRequest
    ) {
        Long userId = getUserIdBySession(httpServletRequest);
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, userId, dto);
        return ApiResponseDto.OK(scheduleResponseDto, "id " + id + " 일정 수정 성공");
    }

    @DeleteMapping("/{id}")
    public ApiResponseDto<Void> deleteSchedule(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest
    ) {
        Long userId = getUserIdBySession(httpServletRequest);
        scheduleService.deleteSchedule(id, userId);
        return ApiResponseDto.OK("id " + id + " 일정 삭제");
    }

    private Long getUserIdBySession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        return (Long) session.getAttribute("userId");
    }

}
