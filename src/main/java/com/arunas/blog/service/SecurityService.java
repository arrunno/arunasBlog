package com.arunas.blog.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public boolean rightToChange(String objectOwnerId){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return false;
    }



}
