package com.arunas.blog.data;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class User {

    public static String getLoggedUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loggedUser;
        if (principal instanceof UserDetails) {
            loggedUser = ((UserDetails)principal).getUsername();
        } else {
            loggedUser = "guest";
        }
        return loggedUser;
    }
}
