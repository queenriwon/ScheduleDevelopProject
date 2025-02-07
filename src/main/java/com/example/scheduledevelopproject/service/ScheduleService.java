package com.example.scheduledevelopproject.service;

import com.example.scheduledevelopproject.dto.request.ScheduleCreateRequestDto;
import com.example.scheduledevelopproject.dto.response.ScheduleResponseDto;
import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.repository.ScheduleRepository;
import com.example.scheduledevelopproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<ScheduleResponseDto> findAllSchedule() {
        List<Schedules> allSchedule = scheduleRepository.findAll();
        return allSchedule.stream().map(ScheduleResponseDto::new).collect(Collectors.toList());
    }

    public ScheduleResponseDto findScheduleById(Long id) {
        Schedules findSchedule = scheduleRepository.findSchedulesByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule);
    }
}
