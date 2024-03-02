package com.myproject.BackendApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myproject.BackendApp.model.AssignTasks;


@Repository
public interface AssignTasksRepository extends MongoRepository<AssignTasks, String> {
    
    Optional<AssignTasks>  findByPostId(String postId);
    Integer countByProfileId(String id);
    List<AssignTasks> findByProfileId(String profileId);
}
