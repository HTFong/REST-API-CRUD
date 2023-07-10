package com.example.blog.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTResq {
    private String accessToken;
    private String tokenType = "Bearer";
}
