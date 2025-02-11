package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.Schedules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedules, Long> {

    Optional<Schedules> findSchedulesById(Long id);

    Page<Schedules> findAllByOrderByModifiedAtDesc(Pageable pageable);
}
