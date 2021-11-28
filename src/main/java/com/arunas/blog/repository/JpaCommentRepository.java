package com.arunas.blog.repository;

import com.arunas.blog.data.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
}
