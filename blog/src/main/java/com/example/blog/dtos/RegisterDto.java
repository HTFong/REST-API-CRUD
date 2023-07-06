package com.example.blog.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto {
    private String name;
    private String username;
    private String email;
    private String password;
}