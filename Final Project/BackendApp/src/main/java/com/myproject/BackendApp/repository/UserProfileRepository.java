package com.myproject.BackendApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myproject.BackendApp.model.UserProfile;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    
    boolean existsByUsername(String username);
}
