package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.request.ScheduleCreateRequestDto;
import com.example.scheduledevelopproject.dto.request.ScheduleUpdateRequestDto;
import com.example.scheduledevelopproject.dto.response.PageResponseDto;
import com.example.scheduledevelopproject.dto.response.ScheduleResponseDto;
import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.custom.badrequest.InvalidScheduleUpdateRequestException;
import com.example.scheduledevelopproject.exception.custom.forbidden.ForbiddenScheduleAccessException;
import com.example.scheduledevelopproject.exception.custom.notfound.NotFoundScheduleIdException;
import com.example.scheduledevelopproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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
    public PageResponseDto<ScheduleResponseDto> findAllSchedule(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Page<Schedules> schedulesPage = scheduleRepository.findAllByOrderByModifiedAtDesc(pageable);
        Page<ScheduleResponseDto> scheduleResponseDtoPage = schedulesPage.map(ScheduleResponseDto::new);
        return new PageResponseDto<>(scheduleResponseDtoPage);
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedules findSchedule = findSchedulesByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, Long userId, ScheduleUpdateRequestDto dto) {
        if (dto.getTodoTitle() == null && dto.getTodoContents() == null) {
            throw new InvalidScheduleUpdateRequestException("일정 제목 내용 수정 - 일정 제목, 내용 모두 받지 못함");
        }

        Schedules findSchedule = findSchedulesByIdOrElseThrow(id);
        Users findScheduleUsers = findSchedule.getUsers();

        if (!Objects.equals(userId, findScheduleUsers.getId())) {
            throw new ForbiddenScheduleAccessException("일정 수정 - 해당 로그인 회원이 수정할 수 없는 일정");
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
    public void deleteSchedule(Long id, Long userId) {
        Schedules findSchedule = findSchedulesByIdOrElseThrow(id);
        Users findScheduleUsers = findSchedule.getUsers();

        if (!Objects.equals(userId, findScheduleUsers.getId())) {
            throw new ForbiddenScheduleAccessException("일정 삭제 - 해당 로그인 회원이 삭제할 수 없는 일정");
        }

        scheduleRepository.deleteById(id);
    }

    public Schedules findSchedulesByIdOrElseThrow(Long id) {
        return scheduleRepository.findSchedulesById(id).orElseThrow(()->
                new NotFoundScheduleIdException("입력된 id로 일정을 찾을 수 없음"));
    }
}
