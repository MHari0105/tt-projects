package com.myproject.BackendApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.BackendApp.exception.NotFoundException;
import com.myproject.BackendApp.model.Post;
import com.myproject.BackendApp.repository.PostsRepository;
import com.myproject.BackendApp.service.PostsService;

@Service
public class PostsServiceImpl implements PostsService {
    
    @Autowired
    private PostsRepository postsRepo;

    @Override
    public Post createPost(Post post) {
        return postsRepo.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postsRepo.findAll();
    }

    @Override
    public Post updatePost(String postId, Post post) throws NotFoundException {
        Optional<Post> existing = postsRepo.findById(postId);

        if (existing.isPresent()) {
            Post existingPost = new Post();
            existingPost.setDescription(post.getDescription());
            return postsRepo.save(existingPost);
        }

        else throw new NotFoundException("Post not found");
        
    }

    @Override
    public String deletePost(String postId) {
        if (postsRepo.existsById(postId)) {
            postsRepo.deleteById(postId);
            return "Post deleted";
        }

        else return "Post not found";
    }
    
}
