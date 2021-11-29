package com.arunas.blog.service;

import com.arunas.blog.data.Comment;
import com.arunas.blog.data.Post;
import com.arunas.blog.repository.JpaCommentRepository;
import com.arunas.blog.repository.JpaPostRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final JpaCommentRepository jpaCommentRepository;
    private final JpaPostRepository jpaPostRepository;

    public CommentService(JpaCommentRepository jpaCommentRepository, JpaPostRepository jpaPostRepository) {
        this.jpaCommentRepository = jpaCommentRepository;
        this.jpaPostRepository = jpaPostRepository;
    }

    public void deleteComment(Long id) {

        Comment comment = this.getCommentById(id);
        jpaCommentRepository.delete(comment);
    }

    public Comment getCommentById(long id){
        return jpaCommentRepository.findById(id).get();
    }


}
