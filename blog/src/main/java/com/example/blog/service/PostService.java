package com.example.blog.service;

import com.example.blog.dtos.PostDto;
import com.example.blog.dtos.PostResp;
import com.example.blog.entity.Post;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResp getAllPost(int pageNo, int pageSize, String sortBy, String orderBy);

    PostDto getPostById(long id);

    PostDto updatePost(long id, PostDto postDto);

    void deletePostById(long id);

    List<PostDto> getPostsByCategoryId(long categoryId);

    PostDto mapToDto(Post entity);

    Post mapToEntity(PostDto dto);
}
