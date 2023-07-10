package com.example.blog.controller;

import com.example.blog.dtos.JWTResq;
import com.example.blog.dtos.LoginDto;
import com.example.blog.dtos.RegisterDto;
import com.example.blog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTResq> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JWTResq resp = new JWTResq();
        resp.setAccessToken(token);
        return ResponseEntity.ok(resp);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
