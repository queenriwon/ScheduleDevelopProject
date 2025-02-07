package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.custom.NotFoundUserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersById(Long id);

    default Users findUsersByIdOrElseThrow(Long id) {
        return findUsersById(id).orElseThrow(() ->
                new NotFoundUserId("찾을 수 없는 user id"));
    }
}
