package com.myproject.BackendApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myproject.BackendApp.model.UserProfile;
import java.util.List;
import com.myproject.BackendApp.security.user.Role;


public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    
    boolean existsByUsername(String username);
    List<UserProfile> findByRole(Role role);
}
