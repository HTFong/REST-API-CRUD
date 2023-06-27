package com.example.blog.service.impl;

import com.example.blog.dtos.PostDto;
import com.example.blog.entity.Post;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepo;
    @Override
    public PostDto createPost(PostDto postDto) {
        Post entity = mapToEntity(postDto);
        entity = postRepo.save(entity);
        postDto.setId(entity.getId());
        return postDto;
    }
    @Override
    public List<PostDto> getAllPost() {
        List<Post> entityList = postRepo.findAll();
        List<PostDto> dtoList = entityList.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dtoList;
    }
    @Override
    public PostDto getPostById(long id) {
        Post entity = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        PostDto dto = mapToDto(entity);
        return dto;
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post entity = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        entity.setTitle(postDto.getTitle());
        entity.setDescription(postDto.getDescription());
        entity.setContent(postDto.getContent());
        postRepo.save(entity);
        postDto = mapToDto(entity);
        return postDto;
    }

    @Override
    public void deletePostById(long id) {
        Post entity = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        postRepo.delete(entity);
    }

    private PostDto mapToDto(Post entity) {
        PostDto dto = PostDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .content(entity.getContent())
                .build();
        return dto;
    }
    private Post mapToEntity(PostDto dto) {
        Post entity = Post.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .content(dto.getContent())
                .build();
        return entity;
    }

}