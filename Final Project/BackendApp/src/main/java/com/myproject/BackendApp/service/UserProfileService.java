package com.myproject.BackendApp.service;

import com.myproject.BackendApp.exception.UserNotFound;
import com.myproject.BackendApp.model.UserProfile;

public interface UserProfileService {
    
    public String createUserProfile(String userId, UserProfile userProfile);
    public String updateUserProfile(String userId, UserProfile updatedProfile) throws UserNotFound;

}
