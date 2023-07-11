package com.example.blog.service.impl;

import com.example.blog.dtos.LoginDto;
import com.example.blog.dtos.RegisterDto;
import com.example.blog.entity.Role;
import com.example.blog.entity.User;
import com.example.blog.exception.BlogAPIException;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.security.JwtTokenProvider;
import com.example.blog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepo.existsByUsername(registerDto.getUsername())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already exists");
        }
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already exists");
        }
        User user = User.builder()
                .name(registerDto.getName())
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();
        Role userRole = roleRepo.findByName("ROLE_USER").get();
//        Set<Role> roles = new HashSet<>();
//        roles.add(userRole);
//        user.setRoles(roles);
        user.setRoles(Collections.singleton(userRole));

        userRepo.save(user);

        return "User registered successfully!.";
    }
}
