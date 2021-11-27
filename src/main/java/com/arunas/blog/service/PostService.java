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

//    public List<Post> loadUserPosts(){
//        return postRepository.getUserPosts();
//    }

//    public List<Post> getUserPostsL(){
//        List<Post> postsL = new ArrayList<>();
//        for(Post post : this.fillPosts()) {
//            postsL.add(post);
//        }
//        return postsL;
//    }

    public void fillPosts(){
//    public Map<UUID, Post> fillPosts(){
        Map<UUID, Post> posts = new HashMap<>();
//        for (int i=1; i<=3; i++) {
        //d2192075-42e6-4aff-9636-8e0e7eeb1116, Post 3, Here goes contents 3, 2021-11-13T10:13, arunas@arunas.com
        //66b1252e-4a72-45af-ba4a-a8c9e3f45130, Post 2, Here goes contents 2, 2021-11-12T10:12, arunas@arunas.com
        //26f64f45-cb46-4d42-b2b9-b83b76d89265, Post 1, Here goes contents 1, 2021-11-11T10:11, arunas@arunas.com

//        UUID uuid = UUID.fromString("d2192075-42e6-4aff-9636-8e0e7eeb1116");
        String uuidString = "d2192075-42e6-4aff-9636-8e0e7eeb1116";
        LocalDateTime dateTime1 = LocalDateTime.of(2021, 11, 10, 10, 10);
        String topic1 = "Post 1"; // + i;
        String contents1 = "Here goes contents  1";// + i;
        String authorEmail = "arunas@arunas.com";
        Post post = new Post(uuidString, dateTime1, topic1, contents1, authorEmail, null);

        Comment comment1 = new Comment(null, 1L, "Some praising comment1", LocalDateTime.of(2021, 11, 11, 15, 10), post);
        Comment comment2 = new Comment(null, 2L, "Some praising comment2", LocalDateTime.of(2021, 11, 12, 11, 20), post);
        Comment comment3 = new Comment(null, 3L, "Some criticizing comment3", LocalDateTime.of(2021, 11, 15, 13, 33), post);
        Set<Comment> commentsSet = new HashSet<>(Arrays.asList(comment1, comment2, comment3));

        post.setComments(commentsSet);

        jpaPostRepository.save(post);

        Post pos = jpaPostRepository.getById(uuidString);

    }

    public void addComment(String uuidString, Comment comment){

        Post post = this.getPostById(uuidString);
//        Post post;
//        if(postOpt.isPresent()){
//            post = postOpt.get();
//        } else {
//            post = new Post();
//        }
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
//        List<Post> allPosts = new ArrayList<>();
        List<Post> allPosts = jpaPostRepository.findAll();
        return allPosts;
    }

    public void savePosts(){
//        List<Post> posts = this.loadUserPosts();
//        for(Post post : posts){
//            jpaPostRepository.save(post);
//        }
    }

    public Post getPostById(String uuidString){
        return jpaPostRepository.findById(uuidString).orElseThrow(() -> new PostDoesNotExistException(uuidString));
    }

    public void savePost(Post post){
        String uuidString = UUID.randomUUID().toString();
        post.setId(uuidString);
        jpaPostRepository.save(post);
    }

}
