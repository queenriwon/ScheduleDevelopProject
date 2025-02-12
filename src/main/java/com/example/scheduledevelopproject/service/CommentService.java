package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.request.CommentRequestDto;
import com.example.scheduledevelopproject.dto.response.CommentResponseDto;
import com.example.scheduledevelopproject.dto.response.PageResponseDto;
import com.example.scheduledevelopproject.entity.Comments;
import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.custom.notfound.NotFoundCommentIdException;
import com.example.scheduledevelopproject.exception.custom.forbidden.ForbiddenCommentAccessException;
import com.example.scheduledevelopproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleService scheduleService;

    public CommentResponseDto createComment(Long scheduleId, Users users, CommentRequestDto dto) {
        Schedules findSchedule = scheduleService.findSchedulesByIdOrElseThrow(scheduleId);

        Comments comments = new Comments(dto);
        comments.setSchedules(findSchedule);
        comments.setUsers(users);

        Comments saveComment = commentRepository.save(comments);
        return new CommentResponseDto(saveComment);
    }

    public PageResponseDto<CommentResponseDto> findAllComment(Long scheduleId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Schedules findSchedule = scheduleService.findSchedulesByIdOrElseThrow(scheduleId);
        Page<Comments> commentsPage = commentRepository.findCommentsBySchedules_IdOrderByModifiedAtDesc(findSchedule.getId(), pageable);
        Page<CommentResponseDto> commentResponseDtoPage = commentsPage.map(CommentResponseDto::new);
        return new PageResponseDto<>(commentResponseDtoPage);
    }

    public CommentResponseDto updateComment(Long commentId, Long userId, CommentRequestDto dto) {
        Comments findComment = findCommentsByIdOrElseThrow(commentId);
        Users findCommentUsers = findComment.getUsers();

        if (!Objects.equals(userId, findCommentUsers.getId())) {
            throw new ForbiddenCommentAccessException("댓글 수정 - 해당 로그인 회원이 수정할 수 없는 댓글");
        }
        findComment.updateContents(dto.getContents());

        return new CommentResponseDto(findCommentsByIdOrElseThrow(commentId));
    }

    public void deleteComment(Long commentId, Long userId) {
        Comments findComment = findCommentsByIdOrElseThrow(commentId);
        Users findCommentUsers = findComment.getUsers();

        if (!Objects.equals(userId, findCommentUsers.getId())) {
            throw new ForbiddenCommentAccessException("댓글 삭제 - 해당 로그인 회원이 삭제할 수 없는 댓글");
        }
        commentRepository.deleteById(commentId);
    }

    private Comments findCommentsByIdOrElseThrow(Long id) {
        return commentRepository.findCommentsById(id).orElseThrow(() ->
                new NotFoundCommentIdException("입력된 id로 댓글을 찾을 수 없음"));
    }
}
