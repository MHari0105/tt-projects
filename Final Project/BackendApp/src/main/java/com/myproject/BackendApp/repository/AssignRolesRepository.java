package com.myproject.BackendApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myproject.BackendApp.model.AssignRoles;

@Repository
public interface AssignRolesRepository extends MongoRepository<AssignRoles, String> {
    
}
