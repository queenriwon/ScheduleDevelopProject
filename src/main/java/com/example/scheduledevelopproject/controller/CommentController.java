package com.example.scheduledevelopproject.controller;


import com.example.scheduledevelopproject.dto.request.CommentCreateRequestDto;
import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.dto.response.CommentResponseDto;
import com.example.scheduledevelopproject.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{scheduleId}/comments")
    public ApiResponseDto<CommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @RequestBody CommentCreateRequestDto dto,
            HttpServletRequest httpServletRequest
    ) {
        Long userId = getUserIdBySession(httpServletRequest);
        CommentResponseDto commentResponseDto = commentService.createComment(scheduleId, userId, dto);
        return ApiResponseDto.OK(commentResponseDto,"댓글 작성 성공");
    }

    private Long getUserIdBySession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        return (Long) session.getAttribute("userId");
    }
}
