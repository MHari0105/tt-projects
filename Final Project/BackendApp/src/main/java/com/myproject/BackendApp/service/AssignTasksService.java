package com.myproject.BackendApp.service;

import java.util.List;

import com.myproject.BackendApp.model.AssignTasks;

public interface AssignTasksService {
    
    public String assignTask(String profileId, String postId);
    public List<AssignTasks> getAssignedTasks(String profileId);
}
