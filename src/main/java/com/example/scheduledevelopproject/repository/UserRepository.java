package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.Users;
import com.example.scheduledevelopproject.exception.custom.NotFoundUserByEmail;
import com.example.scheduledevelopproject.exception.custom.NotFoundUserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersById(Long id);

    Page<Users> findAllByOrderByModifiedAtDesc(Pageable pageable);

    default Users findUsersByIdOrElseThrow(Long id) {
        return findUsersById(id).orElseThrow(() ->
                new NotFoundUserId("찾을 수 없는 user id"));
    }

    Optional<Users> findUsersByEmail(String email);

    default Users findUsersByEmailOrElseThrow(String email){
        return findUsersByEmail(email).orElseThrow(() ->
                new NotFoundUserByEmail("이메일로 유저를 찾을 수 없습니다."));
    }
}
