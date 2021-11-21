package com.arunas.blog.service;

import com.arunas.blog.data.Post;
import com.arunas.blog.repository.JpaPostRepository;
import com.arunas.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final JpaPostRepository jpaPostRepository;

    public PostService(PostRepository postRepository, JpaPostRepository jpaPostRepository){
        this.postRepository = postRepository;
        this.jpaPostRepository = jpaPostRepository;
    }

    public List<Post> loadUserPosts(){
        return postRepository.getUserPosts();
    }

    public List<Post> getPosts(){
//        List<Post> allPosts = new ArrayList<>();
        List<Post> allPosts = jpaPostRepository.findAll();
        return allPosts;
    }

    public void savePosts(){
        List<Post> posts = this.loadUserPosts();
        for(Post post : posts){
            jpaPostRepository.save(post);
        }
    }

}
