package com.myproject.ChartData.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myproject.ChartData.model.LocationData;

@Repository
public interface LocationRepository extends MongoRepository<LocationData, String> {
    
}
