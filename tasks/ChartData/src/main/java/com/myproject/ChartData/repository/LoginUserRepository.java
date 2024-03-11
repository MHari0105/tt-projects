package com.myproject.ChartData.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myproject.ChartData.model.LoginUser;

@Repository
public interface LoginUserRepository extends MongoRepository<LoginUser, String> {
    
}
