package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findCommentsBySchedules_Id(Long scheduleId);

    Optional<Comments> findCommentsById(Long id);
}
