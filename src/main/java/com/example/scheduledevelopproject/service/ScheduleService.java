package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.config.PasswordEncoder;
import com.example.scheduledevelopproject.dto.request.ScheduleCreateRequestDto;
import com.example.scheduledevelopproject.dto.request.ScheduleUpdateRequestDto;
import com.example.scheduledevelopproject.dto.response.ScheduleResponseDto;
import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.custom.InvalidScheduleUpdateRequestException;
import com.example.scheduledevelopproject.exception.custom.NotFoundScheduleId;
import com.example.scheduledevelopproject.exception.custom.PasswordMismatchException;
import com.example.scheduledevelopproject.exception.custom.UnauthorizedScheduleAccessException;
import com.example.scheduledevelopproject.repository.ScheduleRepository;
import com.example.scheduledevelopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto createSchedule(Long userId, ScheduleCreateRequestDto dto) {
        Users findUser = userService.findUsersByIdOrElseThrow(userId);

        Schedules schedules = new Schedules(dto);
        schedules.setUsers(findUser);

        Schedules saveSchedule = scheduleRepository.save(schedules);

        return new ScheduleResponseDto(saveSchedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAllSchedule() {
        List<Schedules> allSchedule = scheduleRepository.findAll();
        return allSchedule.stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedules findSchedule = scheduleRepository.findSchedulesById(id).orElseThrow(()->
                new NotFoundScheduleId("찾을 수 없는 일정"));
        return new ScheduleResponseDto(findSchedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, Long userId, ScheduleUpdateRequestDto dto) {
        if (dto.getTodoTitle() == null && dto.getTodoContents() == null) {
            throw new InvalidScheduleUpdateRequestException("일정 제목, 내용 모두 받지 못함");
        }

        Schedules findSchedule = findSchedulesByIdOrElseThrow(id);
        Users findScheduleUsers = findSchedule.getUsers();

        if (!Objects.equals(userId, findScheduleUsers.getId())) {
            throw new UnauthorizedScheduleAccessException("일정 수정 권한 없음");
        }

        if (dto.getTodoTitle() != null) {
            findSchedule.updateTodoTitle(dto.getTodoTitle());
        }
        if (dto.getTodoContents() != null) {
            findSchedule.updateTodoContents(dto.getTodoContents());
        }

        return findScheduleById(id);
    }

    @Transactional
    public void deleteSchedule(Long id, Long userId, String password) {
        Schedules findSchedule = findSchedulesByIdOrElseThrow(id);
        Users findScheduleUsers = findSchedule.getUsers();

        if (!Objects.equals(userId, findScheduleUsers.getId())) {
            throw new UnauthorizedScheduleAccessException("일정 수정 권한 없음");
        }

        scheduleRepository.deleteById(id);
    }

    public Schedules findSchedulesByIdOrElseThrow(Long id) {
        return scheduleRepository.findSchedulesById(id).orElseThrow(()->
                new NotFoundScheduleId("찾을 수 없는 일정"));
    }
}
