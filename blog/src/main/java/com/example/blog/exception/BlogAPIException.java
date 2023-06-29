package com.example.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{
    public HttpStatus httpStatus;
    public String message;

    public BlogAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public BlogAPIException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }
}
