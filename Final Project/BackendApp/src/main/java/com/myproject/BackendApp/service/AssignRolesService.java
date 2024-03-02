package com.myproject.BackendApp.service;

public interface AssignRolesService {
    
    public String addRoleHigherOfficial(String profileId);
    public String addRoleSupervisor(String profileId);
    public String addRoleWorker(String profileId);

}
