package com.myproject.BackendApp.service;

import java.util.List;

import com.myproject.BackendApp.exception.NotFoundException;
import com.myproject.BackendApp.model.Post;

public interface PostsService {
    
    public Post createPost(Post post);
    public List<Post> getAllPosts();
    public Post updatePost(String postId, Post post) throws NotFoundException;
    public String deletePost(String postId);

}
