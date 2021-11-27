package com.arunas.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDoesNotExistException extends RuntimeException{
    private final String uuidString;
}
