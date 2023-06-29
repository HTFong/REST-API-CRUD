package com.example.blog.service.impl;

import com.example.blog.dtos.CommentDto;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.exception.BlogAPIException;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        comment = commentRepo.save(comment);
        commentDto = mapToDto(comment);
        commentDto.setPost(postService.mapToDto(post));
        return commentDto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> commentDtos = commentRepo.findByPost_Id(postId);
        List<CommentDto> commentDtoList = commentDtos.stream()
                .map(e -> {
                    CommentDto dto = mapToDto(e);
                    dto.setPost(postService.mapToDto(e.getPost()));
                    return dto;
                })
                .collect(Collectors.toList());

        return commentDtoList;
    }

    @Override
    public CommentDto getCommentById(long postId, long id) {
        Comment commentEntity = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(id)));
        if (postId != commentEntity.getPost().getId()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesnt belong to post");
        }
        CommentDto commentDto = mapToDto(commentEntity);
        commentDto.setPost(postService.mapToDto(commentEntity.getPost()));
        return commentDto;
    }

    @Override
    public CommentDto updateCommment(long postId, long id, CommentDto commentDto) {
        Comment commentEntity = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(id)));
        Post postEntity = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

        commentEntity.setPost(postEntity);
        commentEntity.setId(id);
        commentEntity.setName(commentDto.getName());
        commentEntity.setEmail(commentDto.getEmail());
        commentEntity.setBody(commentDto.getBody());
        commentRepo.save(commentEntity);

        CommentDto rs = mapToDto(commentEntity);
        rs.setPost(postService.mapToDto(postEntity));
        return rs;
    }

    @Override
    public void deleteComment(long postId, long id) {
        Comment commentEntity = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(id)));
        if (postId != commentEntity.getPost().getId()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesnt belong to post");
        }
        commentRepo.delete(commentEntity);
    }
    @Override
    public CommentDto mapToDto(Comment entity) {
        return modelMapper.map(entity, CommentDto.class);
//        return CommentDto.builder()
//                .id(entity.getId())
//                .body(entity.getBody())
//                .email(entity.getEmail())
//                .name(entity.getName())
//                .build();
    }

    @Override
    public Comment mapToEntity(CommentDto dto) {
        return modelMapper.map(dto, Comment.class);
//        return Comment.builder()
//                .id(dto.getId())
//                .body(dto.getBody())
//                .email(dto.getEmail())
//                .name(dto.getName())
//                .build();
    }

}
