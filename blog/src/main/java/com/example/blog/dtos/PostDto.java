package com.example.blog.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private long id; // just holding data, no need to wrapper
    private String title;
    private String description;
    private String content;
}
