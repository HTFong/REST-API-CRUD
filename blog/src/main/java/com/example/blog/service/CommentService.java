package com.example.blog.service;

import com.example.blog.dtos.CommentDto;
import com.example.blog.entity.Comment;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId, long id);

    CommentDto updateCommment(long postId, long id, CommentDto commentDto);

    void deleteComment(long postId, long id);

    CommentDto mapToDto(Comment entity);

    Comment mapToEntity(CommentDto dto);
}
