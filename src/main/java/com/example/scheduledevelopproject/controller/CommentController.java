package com.example.scheduledevelopproject.controller;


import com.example.scheduledevelopproject.dto.request.CommentRequestDto;
import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.dto.response.CommentResponseDto;
import com.example.scheduledevelopproject.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{scheduleId}/comments")
    public ApiResponseDto<CommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @RequestBody CommentRequestDto dto,
            HttpServletRequest httpServletRequest
    ) {
        Long userId = getUserIdBySession(httpServletRequest);
        CommentResponseDto commentResponseDto = commentService.createComment(scheduleId, userId, dto);
        return ApiResponseDto.OK(commentResponseDto,scheduleId + " 일정 댓글 작성");
    }

    @GetMapping("/{scheduleId}/comments")
    public ApiResponseDto<List<CommentResponseDto>> findAllComment(@PathVariable Long scheduleId) {
        List<CommentResponseDto> commentResponseDtoList = commentService.findAllComment(scheduleId);
        return ApiResponseDto.OK(commentResponseDtoList, scheduleId + " 일정 댓글 조회");
    }

    @PatchMapping("/{scheduleId}/comments/{commentId}")
    public ApiResponseDto<CommentResponseDto> updateComment(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto dto,
            HttpServletRequest httpServletRequest
    ) {
        Long userId = getUserIdBySession(httpServletRequest);
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, userId, dto);
        return ApiResponseDto.OK(commentResponseDto,scheduleId + " 일정 댓글 수정");
    }

    @DeleteMapping("/{scheduleId}/comments/{commentId}")
    public ApiResponseDto<Void> deleteComment(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId,
            HttpServletRequest httpServletRequest
    ) {
        Long userId = getUserIdBySession(httpServletRequest);
        commentService.deleteComment(commentId, userId);
        return ApiResponseDto.OK(scheduleId + " 일정 댓글 삭제");
    }

    private Long getUserIdBySession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        return (Long) session.getAttribute("userId");
    }
}
