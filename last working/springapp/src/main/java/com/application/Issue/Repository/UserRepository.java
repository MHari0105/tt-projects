package com.application.Issue.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.Issue.Model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	User findByUserName(String userName);
}
