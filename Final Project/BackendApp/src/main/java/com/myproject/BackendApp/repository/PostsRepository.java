package com.myproject.BackendApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myproject.BackendApp.model.Post;

@Repository
public interface PostsRepository extends MongoRepository<Post, String> {
    
}