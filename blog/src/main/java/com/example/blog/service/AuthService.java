package com.example.blog.service;

import com.example.blog.dtos.LoginDto;
import com.example.blog.dtos.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
