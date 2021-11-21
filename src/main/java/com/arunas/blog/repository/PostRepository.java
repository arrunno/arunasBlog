package com.arunas.blog.repository;

import com.arunas.blog.data.Comment;
import com.arunas.blog.data.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class PostRepository {

    private final Map<UUID, Post> posts;

    public PostRepository() {
        this.posts = fillPosts();
    }

    public Map<UUID, Post> fillPosts(){
        Map<UUID, Post> posts = new HashMap<>();
//        for (int i=1; i<=3; i++) {
        //d2192075-42e6-4aff-9636-8e0e7eeb1116, Post 3, Here goes contents 3, 2021-11-13T10:13, arunas@arunas.com
        //66b1252e-4a72-45af-ba4a-a8c9e3f45130, Post 2, Here goes contents 2, 2021-11-12T10:12, arunas@arunas.com
        //26f64f45-cb46-4d42-b2b9-b83b76d89265, Post 1, Here goes contents 1, 2021-11-11T10:11, arunas@arunas.com

        UUID uuid1 = UUID.fromString("d2192075-42e6-4aff-9636-8e0e7eeb1116");
            LocalDateTime dateTime1 = LocalDateTime.of(2021, 11, 10, 10, 10);
            String topic1 = "Post 1"; // + i;
            String contents1 = "Here goes contents  1";// + i;
            String authorEmail = "arunas@arunas.com";

        Comment comment1 = new Comment(null, 1L, "Some praising comment1", LocalDateTime.of(2021, 11, 11, 15, 10));
        Comment comment2 = new Comment(null, 2L, "Some praising comment2", LocalDateTime.of(2021, 11, 12, 11, 20));
        Comment comment3 = new Comment(null, 3L, "Some criticizing comment3", LocalDateTime.of(2021, 11, 15, 13, 33));
        Set<Comment> commentsSet = new HashSet<>(Arrays.asList(comment1, comment2, comment3));

        Post post = new Post(uuid1, dateTime1, topic1, contents1, authorEmail, commentsSet);


            posts.put(uuid1, post);
//        }
        return posts;
    }

    public List<Post> getUserPosts(){
        List<Post> postsL = new ArrayList<>();
        for(Post post : this.posts.values()){
            postsL.add(post);
        }

        return postsL;
    }
}
