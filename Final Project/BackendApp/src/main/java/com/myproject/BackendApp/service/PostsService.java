package com.myproject.BackendApp.service;

import java.util.List;

import com.myproject.BackendApp.exception.NotFoundException;
import com.myproject.BackendApp.model.Post;

public interface PostsService {
    
    public Post createPost(String userId, Post post) throws NotFoundException;
    public List<Post> getAllPosts(String userId);
    public Post userUpdatePost(String postId, Post post) throws NotFoundException;
    public Post updatePostStatus(String postId, Post post) throws NotFoundException;
    public String deletePost(String userId, String postId);

}
