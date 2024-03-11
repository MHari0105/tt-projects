package com.myproject.ChartData.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myproject.ChartData.model.RegisterUser;

@Repository
public interface RegistereUserRepository extends MongoRepository<RegisterUser, String> {
    
}
