package com.arunas.blog.service;

import com.arunas.blog.data.Comment;
import com.arunas.blog.data.Post;
import com.arunas.blog.exception.PostDoesNotExistException;
import com.arunas.blog.repository.JpaPostRepository;
import com.arunas.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final JpaPostRepository jpaPostRepository;

    public PostService(PostRepository postRepository, JpaPostRepository jpaPostRepository){
        this.postRepository = postRepository;
        this.jpaPostRepository = jpaPostRepository;
    }

    public void fillPosts(){
        Map<UUID, Post> posts = new HashMap<>();
        String uuidString = "d2192075-42e6-4aff-9636-8e0e7eeb1116";
        LocalDateTime dateTime1 = LocalDateTime.of(2021, 11, 10, 10, 10);
        String topic1 = "Post 1"; // + i;
        String contents1 = "Here goes contents  1";// + i;
        String authorEmail = "arunas@admin.com";
        Post post = new Post(uuidString, dateTime1, topic1, contents1, authorEmail, null);

        Comment comment1 = new Comment(null, "arunas@admin.com", "Some praising comment1", LocalDateTime.of(2021, 11, 11, 15, 10), post);
        Comment comment2 = new Comment(null, "ona@user.com", "Some praising comment2", LocalDateTime.of(2021, 11, 12, 11, 20), post);
        Comment comment3 = new Comment(null, "petras@user.com", "Some criticizing comment3", LocalDateTime.of(2021, 11, 15, 13, 33), post);
        Set<Comment> commentsSet = new HashSet<>(Arrays.asList(comment1, comment2, comment3));

        post.setComments(commentsSet);
        jpaPostRepository.save(post);
    }

    public void addComment(String uuidString, Comment comment){

        Post post = this.getPostById(uuidString);
        Set<Comment> comments = post.getComments();
        comment.setPost(post);
        comments.add(comment);
        post.setComments(comments);
        jpaPostRepository.save(post);
    }

    public Post getById(String uuidString){
        return jpaPostRepository.getById(uuidString);
    }

    public List<Post> getPosts(){
        List<Post> allPosts = jpaPostRepository.findAll();
        return allPosts;
    }

    public Post getPostById(String uuidString){
        return jpaPostRepository.findById(uuidString).orElseThrow(() -> new PostDoesNotExistException(uuidString));
    }

    public void savePost(Post post){
        String uuidString = UUID.randomUUID().toString();
        post.setId(uuidString);
        jpaPostRepository.save(post);
    }

    public void deletePost(String id) {
        jpaPostRepository.deleteById(id);
    }

}
