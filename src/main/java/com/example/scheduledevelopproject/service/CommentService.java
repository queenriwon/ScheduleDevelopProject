package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.request.CommentRequestDto;
import com.example.scheduledevelopproject.dto.response.CommentResponseDto;
import com.example.scheduledevelopproject.entity.Comments;
import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.custom.NotFoundCommentId;
import com.example.scheduledevelopproject.exception.custom.NotFoundScheduleId;
import com.example.scheduledevelopproject.exception.custom.UnauthorizedCommentAccessException;
import com.example.scheduledevelopproject.exception.custom.UnauthorizedScheduleAccessException;
import com.example.scheduledevelopproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleService scheduleService;
    private final UserService userService;

    public CommentResponseDto createComment(Long scheduleId, Long userId, CommentRequestDto dto) {
        Schedules findSchedule = scheduleService.findSchedulesByIdOrElseThrow(scheduleId);
        Users findUser = userService.findUsersByIdOrElseThrow(userId);

        Comments comments = new Comments(dto);
        comments.setSchedules(findSchedule);
        comments.setUsers(findUser);

        Comments saveComment = commentRepository.save(comments);
        return new CommentResponseDto(saveComment);
    }

    public List<CommentResponseDto> findAllComment(Long scheduleId) {
        Schedules findSchedule = scheduleService.findSchedulesByIdOrElseThrow(scheduleId);
        List<Comments> commentsList = commentRepository.findCommentsBySchedules_Id(findSchedule.getId());
        return commentsList.stream().map(CommentResponseDto::new).toList();
    }

    public CommentResponseDto updateComment(Long commentId, Long userId, CommentRequestDto dto) {
        Comments findComment = findCommentsByIdOrElseThrow(commentId);
        Users findCommentUsers = findComment.getUsers();

        if (!Objects.equals(userId, findCommentUsers.getId())) {
            throw new UnauthorizedCommentAccessException("댓글 수정 권한 없음");
        }
        findComment.updateContents(dto.getContents());

        return new CommentResponseDto(findCommentsByIdOrElseThrow(commentId));
    }

    public void deleteComment(Long commentId, Long userId) {
        Comments findComment = findCommentsByIdOrElseThrow(commentId);
        Users findCommentUsers = findComment.getUsers();

        if (!Objects.equals(userId, findCommentUsers.getId())) {
            throw new UnauthorizedCommentAccessException("댓글 삭제 권한 없음");
        }
        commentRepository.deleteById(commentId);
    }

    private Comments findCommentsByIdOrElseThrow(Long id) {
        return commentRepository.findCommentsById(id).orElseThrow(() ->
                new NotFoundCommentId("일정 id를 찾지 못함"));
    }
}
