package com.myproject.BackendApp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "assign_tasks")
public class AssignTasks {
    
    private String id;
    private String postId;   
    private String profileId;

}
