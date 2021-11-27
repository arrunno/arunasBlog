package com.arunas.blog.controller;

import com.arunas.blog.data.Comment;
import com.arunas.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class CommentController {

    private final PostService postService;

    public CommentController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/createComment", method = RequestMethod.POST)
    public String createCommentController(Model model, HttpServletRequest rq){

        model.addAttribute("comment", rq.getParameter("comment"));
        String uuidSpostId = rq.getParameter("postId");
        Comment comment = new Comment(null, 1L, rq.getParameter("comment"), LocalDateTime.now(), null);
        postService.addComment(uuidSpostId, comment);
        model.addAttribute("posts", postService.getPosts());
        return "redirect:/";
    }

    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public String showComment(Model model, HttpServletRequest rq){

        model.addAttribute("comment", rq.getParameter("comment"));
        model.addAttribute("postId", rq.getParameter("id"));
        return "comment";
    }
}
