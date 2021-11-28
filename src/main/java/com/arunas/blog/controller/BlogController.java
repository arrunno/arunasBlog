package com.arunas.blog.controller;

import com.arunas.blog.data.Post;
import com.arunas.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
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
    public String initPostsController(Model model){

        model.addAttribute("posts", postService.getPosts());
        return "blog";
    }

    @GetMapping("posts")
    public String showPosts(Model model){
        model.addAttribute("posts", postService.getPosts());
        return "blog";
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable String id) {
        postService.deletePost(id);

        return "redirect:/";
    }

    @GetMapping("createPost")
    public String loadPostForm(Model model){
        model.addAttribute("post", new Post());
        return "post";
    }

    @PostMapping("createPost")
    public String createPost(Post post, Model model){

        post.setPostDate(LocalDateTime.now());
        post.setComments(new HashSet<>());
        postService.savePost(post);
        model.addAttribute("posts", postService.getPosts());
        return "redirect:/";
    }

    @GetMapping("contacts")
    public String showContacts(){
        return "contacts";
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

}
