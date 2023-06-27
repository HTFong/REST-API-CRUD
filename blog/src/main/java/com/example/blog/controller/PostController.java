package com.example.blog.controller;

import com.example.blog.dtos.PostDto;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post) {
        PostDto rs = postService.createPost(post);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }
    @GetMapping
    private ResponseEntity<List<PostDto>> getAllPost() {
        List<PostDto> dtoList = postService.getAllPost();
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable long id) {
        PostDto postDto = postService.getPostById(id);
        return ResponseEntity.ok(postDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable long id) {
        PostDto rs = postService.updatePost(id, postDto);
        return ResponseEntity.ok(rs);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Delete success");
    }
}
