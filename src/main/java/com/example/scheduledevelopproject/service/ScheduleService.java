package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.request.ScheduleCreateRequestDto;
import com.example.scheduledevelopproject.dto.request.ScheduleUpdateRequestDto;
import com.example.scheduledevelopproject.dto.response.ScheduleResponseDto;
import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.custom.InvalidScheduleUpdateRequestException;
import com.example.scheduledevelopproject.exception.custom.PasswordMismatchException;
import com.example.scheduledevelopproject.repository.ScheduleRepository;
import com.example.scheduledevelopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto createSchedule(ScheduleCreateRequestDto dto) {
        Users findUser = userRepository.findUsersByIdOrElseThrow(dto.getUserId());

        Schedules schedules = new Schedules(dto);
        schedules.setUsers(findUser);

        Schedules saveSchedule = scheduleRepository.save(schedules);

        return new ScheduleResponseDto(saveSchedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAllSchedule() {
        List<Schedules> allSchedule = scheduleRepository.findAll();
        return allSchedule.stream().map(ScheduleResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedules findSchedule = scheduleRepository.findSchedulesByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto) {
        if (dto.getTodoTitle() == null && dto.getTodoContents() == null) {
            throw new InvalidScheduleUpdateRequestException("일정 제목, 내용 모두 받지 못함");
        }

        Schedules findSchedule = scheduleRepository.findSchedulesByIdOrElseThrow(id);
        Users findScheduleUsers = findSchedule.getUsers();

        if (!findScheduleUsers.getPassword().equals(dto.getPassword())){
            throw new PasswordMismatchException("비밀번호 불일치");
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
    public void deleteSchedule(Long id, String password) {
        Schedules findSchedule = scheduleRepository.findSchedulesByIdOrElseThrow(id);
        Users findScheduleUsers = findSchedule.getUsers();

        if (!findScheduleUsers.getPassword().equals(password)){
            throw new PasswordMismatchException("비밀번호 불일치");
        }
        scheduleRepository.deleteById(id);
    }
}
