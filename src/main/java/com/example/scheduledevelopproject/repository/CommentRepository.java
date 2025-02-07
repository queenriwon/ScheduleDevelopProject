package com.example.scheduledevelopproject.repository;

import com.example.scheduledevelopproject.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Long> {
}
