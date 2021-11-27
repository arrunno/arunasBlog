package com.arunas.blog.controller;

import com.arunas.blog.data.Comment;
import com.arunas.blog.data.Post;
import com.arunas.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class BlogController {

    private final PostService postService;

    public BlogController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("")
    public String initHelloController(Model model){

        model.addAttribute("posts", postService.getPosts());
        return "blog";
    }

    @GetMapping("posts")
    public String postsController(Model model){

//        model.addAttribute("posts", postService.loadUserPosts());
        model.addAttribute("posts", postService.getPosts());
        return "";
    }

    @GetMapping("createPost")
    public String loadPostForm(Model model){
        model.addAttribute("post", new Post());
        return "post";
    }

    @PostMapping("createPost")
    public String createPost(Post post, Model model){

//        System.out.println(post.getTopic() + " " + post.getContents());
        post.setPostDate(LocalDateTime.now());
        post.setComments(new HashSet<>());
        postService.savePost(post);
        model.addAttribute("posts", postService.getPosts());
        return "redirect:/";
    }

    @GetMapping("kontaktai")
    public String kontaktaiController(){
        return "kontaktai";
    }

    @GetMapping("login")
    public String loginController(){
        return "login";
    }

    @GetMapping("fillDbPosts")
    public String fillPostsController(Model model){

        postService.fillPosts();
        model.addAttribute("posts", postService.getPosts());
        return "blog";
    }

    @GetMapping("addComment")
    public String addCommentController(Model model){
//        String uuidString = "d2192075-42e6-4aff-9636-8e0e7eeb1116";
//        UUID uuid = UUID.fromString("d2192075-42e6-4aff-9636-8e0e7eeb1116");
        String uuidString = "d2192075-42e6-4aff-9636-8e0e7eeb1116";
//        Post post = postService.getPostById(uuid);
        Comment comment = new Comment(null, 1L, "Some additional comment", LocalDateTime.now(), null);
        postService.addComment(uuidString, comment);
        model.addAttribute("posts", postService.getPosts());
        return "redirect:/";
    }


}
