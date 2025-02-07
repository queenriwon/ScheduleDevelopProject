package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
