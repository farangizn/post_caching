package org.example.module10homework2.service;

import lombok.RequiredArgsConstructor;
import org.example.module10homework2.dto.PostDTO;
import org.example.module10homework2.entity.Post;
import org.example.module10homework2.mappers.PostMapper;
import org.example.module10homework2.repo.PostRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Cacheable(value = "post")
    public List<PostDTO> getAllCached() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PostDTO> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }
}
