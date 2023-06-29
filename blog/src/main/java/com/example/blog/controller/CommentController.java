package com.example.blog.controller;

import com.example.blog.dtos.CommentDto;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto) {
        CommentDto rs = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable long postId) {
        List<CommentDto> commentDtos = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentsById(@PathVariable long postId, @PathVariable long id) {
        CommentDto commentDto = commentService.getCommentById(postId, id);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long postId, @PathVariable long id, @RequestBody CommentDto commentDto) {
        CommentDto rs = commentService.updateCommment(postId, id, commentDto);
        return new ResponseEntity<>(rs, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable long postId, @PathVariable long id) {
       commentService.deleteComment(postId, id);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);

    }
}
