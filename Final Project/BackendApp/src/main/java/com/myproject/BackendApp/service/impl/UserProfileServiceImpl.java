package com.myproject.BackendApp.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.ArrayList;

import java.text.SimpleDateFormat;  
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.BackendApp.exception.UserNotFound;
import com.myproject.BackendApp.model.UserProfile;
import com.myproject.BackendApp.repository.UserProfileRepository;
import com.myproject.BackendApp.security.repository.UserRepository;
import com.myproject.BackendApp.security.user.User;
import com.myproject.BackendApp.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private UserProfileRepository userProfileRepo;

    @Override
    public String createUserProfile(String userId, UserProfile userProfile) {

        Optional<User> existing = userRepo.findById(userId);

        if (existing.isPresent()) {
            User user = existing.get();

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

            userProfile.setUserId(userId);
            userProfile.setUsername(username);
            userProfile.setRole(user.getRole());
            userProfile.setTotalPosts(0);
            userProfile.setAllPosts(new ArrayList<>());

            String dobString = userProfile.getDobString();

            if (dobString != null && !dobString.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Date dob = dateFormat.parse(dobString);
                    userProfile.setDob(dob);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            userProfileRepo.save(userProfile);
        }

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
