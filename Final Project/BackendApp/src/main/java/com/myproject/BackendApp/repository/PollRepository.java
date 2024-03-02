package com.myproject.BackendApp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myproject.BackendApp.model.Poll;


@Repository
public interface PollRepository extends MongoRepository<Poll, String> {
    
    Optional<Poll> findByPostId(String postId);
}
