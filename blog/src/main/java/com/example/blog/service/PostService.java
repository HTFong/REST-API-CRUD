package com.example.blog.service;

import com.example.blog.dtos.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPost();

    PostDto getPostById(long id);

    PostDto updatePost(long id, PostDto postDto);

    void deletePostById(long id);
}
