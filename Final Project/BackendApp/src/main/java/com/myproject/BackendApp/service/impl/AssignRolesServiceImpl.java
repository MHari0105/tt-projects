package com.myproject.BackendApp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.BackendApp.model.UserProfile;
import com.myproject.BackendApp.repository.UserProfileRepository;
import com.myproject.BackendApp.security.user.Role;
import com.myproject.BackendApp.service.AssignRolesService;

@Service
public class AssignRolesServiceImpl implements AssignRolesService {

    @Autowired
    private UserProfileRepository userProfileRepo;

    @Override
    public String addRoleHigherOfficial(String profileId) {
        Optional<UserProfile> existing = userProfileRepo.findById(profileId);

        if (existing.isPresent()) {
            UserProfile user = existing.get();
            user.setRole(Role.HIGHER_OFFICIAL);
            return "Role updated Higher official";
        }

        return "User Profile not found";
    }

    @Override
    public String addRoleSupervisor(String profileId) {  
        Optional<UserProfile> existing = userProfileRepo.findById(profileId);

        if (existing.isPresent()) {
            UserProfile user = existing.get();
            user.setRole(Role.SUPERVISOR);
            return "ROle updated Supervisor";
        }

        return "User Profile not found";
        
    }

    @Override
    public String addRoleWorker(String profileId) {
        Optional<UserProfile> existing = userProfileRepo.findById(profileId);

        if (existing.isPresent()) {
            UserProfile user = existing.get();
            user.setRole(Role.WORKER);
            return "Role updated Worker";
        }

        return "User Profile not found";
        
    } 
    
}
