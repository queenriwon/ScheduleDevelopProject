package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.request.CommentRequestDto;
import com.example.scheduledevelopproject.dto.response.CommentResponseDto;
import com.example.scheduledevelopproject.entity.Comments;
import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public CommentResponseDto updateComment(Long commentId, Long scheduleId, Long userId, CommentRequestDto dto) {
        commentRepository.findCommentsById(commentId);

    }
}
