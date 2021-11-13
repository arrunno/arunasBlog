package com.arunas.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BlogController {

    @GetMapping("")
    public String initHelloController(){
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


}
