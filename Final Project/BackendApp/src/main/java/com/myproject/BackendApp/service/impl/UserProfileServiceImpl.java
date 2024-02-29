package com.myproject.BackendApp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.BackendApp.exception.UserNotFound;
import com.myproject.BackendApp.model.UserProfile;
import com.myproject.BackendApp.repository.UserProfileRepository;
import com.myproject.BackendApp.security.user.User;
import com.myproject.BackendApp.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    
    @Autowired
    private UserProfileRepository userProfileRepo;


    @Override
    public String createUserProfile(User user) {

        UserProfile userProfile = new UserProfile();

        String userEmail = user.getEmail();
        String emailNum = "";

        for (int i=0; i<userEmail.length(); i++) {
            char ch = userEmail.charAt(i);
            if (Character.isDigit(ch))
                emailNum += ch;
        }

        String basename = user.getFirstname().toLowerCase() + user.getLastname().toLowerCase() + "_" + emailNum;
        String username = basename;
        int counter = 0;

        while (userProfileRepo.existsByUsername(username)) {
            username = basename + counter;
            counter++;
        }

        userProfile.setUsername(username);
        userProfile.setRole(user.getRole());

        userProfileRepo.save(userProfile);
        return "Profile added";
    }


    @Override
    public String updateUserProfile(String userId, UserProfile updateProfile) throws UserNotFound {
        Optional<UserProfile> existing = userProfileRepo.findById(userId);

        if (existing.isPresent()) {
            UserProfile existingProfile = existing.get();
            existingProfile.setUsername(updateProfile.getUsername());
            userProfileRepo.save(existingProfile);
            return "User updated";
        }

        throw new UserNotFound("User not found");
    }
    
}
