package com.example.blog.service.impl;

import com.example.blog.dtos.CommentDto;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private PostService postService;
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        comment = commentRepo.save(comment);
        commentDto = mapToDto(comment);
        return commentDto;
    }
    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> commentDtos = commentRepo.findByPost_Id(postId);
        return commentDtos.stream().map(e->mapToDto(e)).collect(Collectors.toList());
    }
    public CommentDto getCommentById(long postId, long id) {
        Comment entity = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment","id", String.valueOf(id)));
        if (postId != entity.getPost().getId()) {

        }
        CommentDto commentDto = mapToDto(entity);
        return commentDto;

    }
    @Override
    public CommentDto mapToDto(Comment entity) {
        return CommentDto.builder()
                .id(entity.getId())
                .body(entity.getBody())
                .email(entity.getEmail())
                .name(entity.getName())
                .post(postService.mapToDto(entity.getPost()))
                .build();
    }
    @Override
    public Comment mapToEntity(CommentDto dto) {
        return Comment.builder()
                .id(dto.getId())
                .body(dto.getBody())
                .email(dto.getEmail())
                .name(dto.getName())
                .post(postService.mapToEntity(dto.getPost()))
                .build();
    }

}
