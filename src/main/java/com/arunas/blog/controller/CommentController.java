package com.arunas.blog.controller;

import com.arunas.blog.data.Comment;
import com.arunas.blog.data.User;
import com.arunas.blog.service.CommentService;
import com.arunas.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class CommentController {

    private final PostService postService;
    private final CommentService commentService;

    public CommentController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/private/createComment", method = RequestMethod.POST)
    public String createComment(Model model, HttpServletRequest rq){

        model.addAttribute("comment", rq.getParameter("comment"));
        String uuidSpostId = rq.getParameter("postId");
        Comment comment = new Comment(null, User.getLoggedUser(), rq.getParameter("comment"), LocalDateTime.now(), null);
        postService.addComment(uuidSpostId, comment);
        model.addAttribute("posts", postService.getPosts());
        return "redirect:/public/posts";
    }

    @RequestMapping(value = "/public/comment", method = RequestMethod.GET)
    public String showComment(Model model, HttpServletRequest rq){

        model.addAttribute("comment", rq.getParameter("comment"));
        model.addAttribute("postId", rq.getParameter("id"));
        return "comment";
    }

    @GetMapping("/private/comment/{id}/delete")
    public String deleteComment(@PathVariable Long id) {

        commentService.deleteComment(id);

        return "redirect:/public/posts";
    }

}
