package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    Page<Comments> findCommentsBySchedules_IdOrderByModifiedAtDesc(Long id, Pageable pageable);

    Optional<Comments> findCommentsById(Long id);
}
