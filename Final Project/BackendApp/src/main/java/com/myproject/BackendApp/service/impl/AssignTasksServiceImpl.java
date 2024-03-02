package com.myproject.BackendApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.BackendApp.model.AssignTasks;
import com.myproject.BackendApp.model.Post;
import com.myproject.BackendApp.model.UserProfile;
import com.myproject.BackendApp.repository.AssignTasksRepository;
import com.myproject.BackendApp.repository.PostsRepository;
import com.myproject.BackendApp.repository.UserProfileRepository;
import com.myproject.BackendApp.security.user.Role;
import com.myproject.BackendApp.service.AssignTasksService;

@Service
public class AssignTasksServiceImpl implements AssignTasksService {

    @Autowired
    private AssignTasksRepository assignTasksRepo;

    @Autowired
    private UserProfileRepository userProfileRepo;

    @Autowired
    private PostsRepository postRepo;

    @Override
    public String assignTask(String profileId, String postId) {

        Optional<AssignTasks> existingTask = assignTasksRepo.findByPostId(postId);
        if (existingTask.isPresent())
            return "Tasks already assigned";

        Optional<UserProfile> userProfileOptional = userProfileRepo.findById(profileId);
        if (userProfileOptional.isPresent()) {
            UserProfile userProfile = userProfileOptional.get();

            if (userProfile.getRole() != Role.HIGHER_OFFICIAL) 
                return "Only Higher officials can assign tasks";
        }
        else return "User Profile not found";

        String supervisorId = findSupervisor();

        if (supervisorId != null) {
            AssignTasks taskAssignment = new AssignTasks();
            taskAssignment.setPostId(postId);
            taskAssignment.setProfileId(supervisorId);

            assignTasksRepo.save(taskAssignment);

            Optional<Post> postOpt = postRepo.findById(postId);
            if (postOpt.isPresent()) {
                Post post = postOpt.get();
                post.setPostStatus("on prgocess");
                postRepo.save(post);
            }

            return "Task " + postId + " assigned for " + profileId;
        }
            
        return "No available supervisors found";
        
    }

    @Override
    public List<AssignTasks> getAssignedTasks(String profileId) {
        return assignTasksRepo.findByProfileId(profileId);
    }

    private String findSupervisor() {
        List<UserProfile> supervisors = userProfileRepo.findByRole(Role.SUPERVISOR);
    
        UserProfile selectedSupervisor = null;
        int minTaskCount = Integer.MAX_VALUE;

        for (UserProfile supervisor : supervisors) {
            int taskCount = assignTasksRepo.countByProfileId(supervisor.getId());
            if (taskCount < minTaskCount) {
                minTaskCount = taskCount;
                selectedSupervisor = supervisor;
            }
        }
    
        return (selectedSupervisor != null) ? selectedSupervisor.getId() : null;
    }
    
    
}
