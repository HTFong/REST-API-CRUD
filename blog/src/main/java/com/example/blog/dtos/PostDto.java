package com.example.blog.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "PostDto model info")
public class PostDto {
    private long id; // just holding data, no need to wrapper
    @NotEmpty
    @Size(min = 2,message = "Post title should have at least 2 characters")
    @Schema(description = "Blog title. Ex:blog101")
    private String title;
    @NotEmpty
    @Size(min = 5,message = "Post desc should have at least 5 characters")
    private String description;
    @NotEmpty
    private String content;

    private long categoryId;
}
