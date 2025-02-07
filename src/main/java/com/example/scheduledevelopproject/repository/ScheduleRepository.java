package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedules, Long> {

}
