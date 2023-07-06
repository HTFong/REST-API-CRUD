package com.example.blog.controller;

import com.example.blog.dtos.PostDto;
import com.example.blog.dtos.PostResp;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.service.PostService;
import com.example.blog.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto post) {
        PostDto rs = postService.createPost(post);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<PostResp> getAllPost(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "orderBy", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String orderBy

    ) {
        PostResp postResponse = postService.getAllPost(pageNo, pageSize, sortBy, orderBy);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable long id) {
        PostDto postDto = postService.getPostById(id);
        return ResponseEntity.ok(postDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable long id) {
        PostDto rs = postService.updatePost(id, postDto);
        return ResponseEntity.ok(rs);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Delete success aa");
    }
}
