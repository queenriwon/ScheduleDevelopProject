package com.example.scheduledevelopproject.controller;


import com.example.scheduledevelopproject.annotation.SessionUser;
import com.example.scheduledevelopproject.dto.request.CommentRequestDto;
import com.example.scheduledevelopproject.dto.request.UserSessionDto;
import com.example.scheduledevelopproject.dto.response.ApiResponseDto;
import com.example.scheduledevelopproject.dto.response.CommentResponseDto;
import com.example.scheduledevelopproject.dto.response.PageResponseDto;
import com.example.scheduledevelopproject.service.CommentService;
import jakarta.validation.Valid;
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
            @Valid @RequestBody CommentRequestDto dto,
            @SessionUser UserSessionDto userSession
    ) {
        CommentResponseDto commentResponseDto = commentService.createComment(scheduleId, userSession.getId(), dto);
        return ApiResponseDto.OK(commentResponseDto,scheduleId + " 일정 댓글 작성");
    }

    @GetMapping("/{scheduleId}/comments")
    public ApiResponseDto<PageResponseDto<CommentResponseDto>> findAllComment(
            @PathVariable Long scheduleId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponseDto<CommentResponseDto> allComment = commentService.findAllComment(scheduleId, page, size);
        return ApiResponseDto.OK(allComment, scheduleId + " 일정 댓글 조회");
    }

    @PatchMapping("/{scheduleId}/comments/{commentId}")
    public ApiResponseDto<CommentResponseDto> updateComment(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto dto,
            @SessionUser UserSessionDto userSession
    ) {
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, userSession.getId(), dto);
        return ApiResponseDto.OK(commentResponseDto,scheduleId + " 일정 댓글 수정");
    }

    @DeleteMapping("/{scheduleId}/comments/{commentId}")
    public ApiResponseDto<Void> deleteComment(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId,
            @SessionUser UserSessionDto userSession
    ) {
        commentService.deleteComment(commentId, userSession.getId());
        return ApiResponseDto.OK(scheduleId + " 일정 댓글 삭제");
    }
}
