package com.myproject.BackendApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.BackendApp.exception.UserNotFound;
import com.myproject.BackendApp.model.UserProfile;
import com.myproject.BackendApp.service.UserProfileService;

@RestController
@RequestMapping("/user-profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> createUserProfile(@PathVariable String userId, @RequestBody UserProfile userProfile) {
        try {
            String message = userProfileService.createUserProfile(userId, userProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user profile: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable String id, @RequestBody UserProfile updatedProfile) throws UserNotFound {
        return ResponseEntity.ok(userProfileService.updateUserProfile(id, updatedProfile));
    }
    
}
