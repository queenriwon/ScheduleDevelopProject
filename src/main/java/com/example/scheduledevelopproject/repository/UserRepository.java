package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersById(Long id);

    Page<Users> findAllByOrderByModifiedAtDesc(Pageable pageable);

    Optional<Users> findUsersByEmail(String email);

    boolean existsByEmail(String email);
}
