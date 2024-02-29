package com.myproject.BackendApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myproject.BackendApp.dto.Poll;

@Repository
public interface PollRepository extends MongoRepository<Poll, String> {

}
