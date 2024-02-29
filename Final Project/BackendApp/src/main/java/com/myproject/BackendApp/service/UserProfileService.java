package com.myproject.BackendApp.service;

import com.myproject.BackendApp.exception.UserNotFound;
import com.myproject.BackendApp.model.UserProfile;
import com.myproject.BackendApp.security.user.User;

public interface UserProfileService {
    
    public String createUserProfile(User user);
    public String updateUserProfile(String userId, UserProfile updatedProfile) throws UserNotFound;

}
