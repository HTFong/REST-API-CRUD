package com.example.blog.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private long id; // just holding data, no need to wrapper
    private String title;
    private String description;
    private String content;
}
