package com.example.blog.entity;

import jakarta.persistence.*;
import lombok.*;

// if use @ToString, it cause recursive. Ex: Comment.getPost.toString, Post.getComment.toString over and over >>Out of Stack
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "comments"
)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
