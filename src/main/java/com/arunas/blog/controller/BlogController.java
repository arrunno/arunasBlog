package com.arunas.blog.controller;

import com.arunas.blog.data.Post;
import com.arunas.blog.data.User;
import com.arunas.blog.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.HashSet;

@Controller
public class BlogController {

    private final PostService postService;

    public BlogController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/private/login")
    public String postsAfterLogin(){
        return "redirect:/public/posts";
    }

    @GetMapping("")
    public String initPostsController(Model model){

        model.addAttribute("posts", postService.getPosts());
        model.addAttribute("loggedUser", User.getLoggedUser());
        return "blog";
    }

    @GetMapping("/public/posts")
    public String showPosts(Model model){
        model.addAttribute("posts", postService.getPosts());
        model.addAttribute("loggedUser", User.getLoggedUser());
        return "blog";
    }

    @GetMapping("private/posts/{id}/delete")
    public String deletePost(@PathVariable String id) {
        postService.deletePost(id);

        return "redirect:/public/posts";
    }

    @GetMapping("private/createPost")
    public String loadPostForm(Model model){
        model.addAttribute("post", new Post());
        return "post";
    }

    @PostMapping("private/createPost")
    public String createPost(Post post, Model model){

        post.setPostDate(LocalDateTime.now());
        post.setComments(new HashSet<>());
        post.setAuthorEmail(User.getLoggedUser());
        postService.savePost(post);
        model.addAttribute("posts", postService.getPosts());
        return "redirect:/public/posts";
    }

    @GetMapping("/public/contacts")
    public String showContacts(){
        return "contacts";
    }

    @GetMapping("/private/fillDbPosts")
    public String fillPostsController(Model model){

        postService.fillPosts();
        model.addAttribute("posts", postService.getPosts());
        return "blog";
    }

}
