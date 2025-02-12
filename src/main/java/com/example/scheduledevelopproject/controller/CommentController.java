package com.example.scheduledevelopproject.controller;


import com.example.scheduledevelopproject.annotation.LoginRequired;
import com.example.scheduledevelopproject.annotation.SessionUser;
import com.example.scheduledevelopproject.dto.request.CommentRequestDto;
import com.example.scheduledevelopproject.dto.SessionUserDto;
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

    @LoginRequired
    @PostMapping("/{scheduleId}/comments")
    public ApiResponseDto<CommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CommentRequestDto dto,
            @SessionUser SessionUserDto userSession
    ) {
        CommentResponseDto commentResponseDto = commentService.createComment(scheduleId, userSession.toUsers(), dto);
        log.info("id {} 일정 댓글 작성", scheduleId);
        return ApiResponseDto.OK(commentResponseDto,"id" + scheduleId + " 일정 댓글 작성");
    }

    @GetMapping("/{scheduleId}/comments")
    public ApiResponseDto<PageResponseDto<CommentResponseDto>> findAllComment(
            @PathVariable Long scheduleId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponseDto<CommentResponseDto> allComment = commentService.findAllComment(scheduleId, page, size);
        log.info("id {} 일정 댓글 조회", scheduleId);
        return ApiResponseDto.OK(allComment, "id" + scheduleId + " 일정 댓글 조회");
    }

    @LoginRequired
    @PatchMapping("/comments/{commentId}")
    public ApiResponseDto<CommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto dto,
            @SessionUser SessionUserDto userSession
    ) {
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, userSession.getId(), dto);
        log.info("id {} 댓글 수정", commentId);
        return ApiResponseDto.OK(commentResponseDto,"id" + commentId + " 댓글 수정");
    }

    @LoginRequired
    @DeleteMapping("/comments/{commentId}")
    public ApiResponseDto<Void> deleteComment(
            @PathVariable Long commentId,
            @SessionUser SessionUserDto userSession
    ) {
        commentService.deleteComment(commentId, userSession.getId());
        log.info("id {} 댓글 삭제", commentId);
        return ApiResponseDto.OK("id" + commentId + " 댓글 삭제");
    }
}
