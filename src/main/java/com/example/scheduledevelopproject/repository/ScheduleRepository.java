package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.Schedules;
import com.example.scheduledevelopproject.exception.custom.NotFoundScheduleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedules, Long> {

    Optional<Schedules> findSchedulesById(Long id);

    default Schedules findSchedulesByIdOrElseThrow(Long id) {
        return findSchedulesById(id).orElseThrow(() ->
                new NotFoundScheduleId("찾을 수 없는 일정"));
    }
}
