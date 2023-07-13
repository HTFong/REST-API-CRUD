package com.example.blog.controller;

import com.example.blog.dtos.PostDto;
import com.example.blog.dtos.PostResp;
import com.example.blog.service.PostService;
import com.example.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/posts")
@Tag(
        name = "CRUD-POST"
)
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<PostResp> getAllPost(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "orderBy", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String orderBy

    ) {
        PostResp postResponse = postService.getAllPost(pageNo, pageSize, sortBy, orderBy);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Create Post",
            description = "Used to save post into DB"
    )
    @ApiResponse(
            responseCode = "201", description = "HTTP Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer-Auth"
    ) //must have the same name to @SecurityScheme
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto post) {
        PostDto rs = postService.createPost(post);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get a Post by Id",
            description = "Used to get single post from DB with specific id"
    )
    @ApiResponse(
            responseCode = "200", description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable long id) {
        PostDto postDto = postService.getPostById(id);
        return ResponseEntity.ok(postDto);
    }
    @Operation(
            summary = "Change a Post by Id",
            description = "Used to update post info from DB with specific id"
    )
    @ApiResponse(
            responseCode = "200", description = "HTTP Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer-Auth"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable long id) {
        PostDto rs = postService.updatePost(id, postDto);
        return ResponseEntity.ok(rs);
    }
    @Operation(
            summary = "Delete a Post by Id",
            description = "Used to delete single post from DB with specific id"
    )
    @ApiResponse(
            responseCode = "200", description = "HTTP Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer-Auth"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Delete success aa");
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<PostDto>> getPostByCateId(@PathVariable("id") long categoryId) {
        List<PostDto> posts = postService.getPostsByCategoryId(categoryId);
        return ResponseEntity.ok(posts);
    }

}
