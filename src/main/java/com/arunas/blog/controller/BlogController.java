package com.arunas.blog.controller;

import com.arunas.blog.data.Post;
import com.arunas.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.HashSet;

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

    @GetMapping("create")
    public String loadPostForm(Model model){
        model.addAttribute("post", new Post());
        return "post";
    }

    @PostMapping("create")
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

        postService.savePosts();
        model.addAttribute("posts", postService.getPosts());
        return "blog";
    }


}
