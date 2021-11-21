package com.arunas.blog.repository;

import com.arunas.blog.data.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaPostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByAuthorEmail(String authorEmail);
}
