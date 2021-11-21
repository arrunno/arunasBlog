package com.arunas.blog.controller;

import com.arunas.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BlogController {

    private final PostService postService;

    public BlogController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("")
    public String initHelloController(Model model){

        model.addAttribute("posts", postService.loadUserPosts());
        return "blog";
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
    public String fillPostsController(){

        postService.savePosts();
        return "blog";
    }


}
