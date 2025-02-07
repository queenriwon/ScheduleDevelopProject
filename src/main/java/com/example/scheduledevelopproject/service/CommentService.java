package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.request.CommentCreateRequestDto;
import com.example.scheduledevelopproject.dto.response.CommentResponseDto;
import com.example.scheduledevelopproject.entity.Comments;
import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleService scheduleService;
    private final UserService userService;

    public CommentResponseDto createComment(Long scheduleId, Long userId, CommentCreateRequestDto dto) {
        Schedules findSchedule = scheduleService.findSchedulesByIdOrElseThrow(scheduleId);
        Users findUser = userService.findUsersByIdOrElseThrow(userId);

        Comments comments = new Comments(dto);
        comments.setSchedules(findSchedule);
        comments.setUsers(findUser);

        Comments saveComment = commentRepository.save(comments);
        return new CommentResponseDto(saveComment);
    }
}
