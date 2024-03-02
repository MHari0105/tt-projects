package com.myproject.BackendApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.BackendApp.exception.NotFoundException;
import com.myproject.BackendApp.model.Post;
import com.myproject.BackendApp.service.PostsService;

@RestController
@RequestMapping("/post")
public class PostController {
    
    @Autowired
    private PostsService postService;

    @PostMapping("/{userId}")
    public ResponseEntity<Post> createPost(@PathVariable String userId, @RequestBody Post post) throws NotFoundException {
        return new ResponseEntity<>(postService.createPost(userId, post), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Post>> getAllPosts(@PathVariable String userId) {
        return new ResponseEntity<>(postService.getAllPosts(userId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Post> updateDescription(@PathVariable String postId, @RequestBody Post post) throws NotFoundException {
        return new ResponseEntity<>(postService.userUpdatePost(postId, post), HttpStatus.OK);
    }

    @DeleteMapping("/{profileId}/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String profileId, @PathVariable String postId) {
        return new ResponseEntity<>(postService.deletePost(profileId, postId), HttpStatus.OK);
    }
    
}
