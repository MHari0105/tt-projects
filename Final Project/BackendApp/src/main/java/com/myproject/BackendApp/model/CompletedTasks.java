package com.myproject.BackendApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "all_tasks")
public class CompletedTasks {
    
    @Id
    private String id;
    private String taskId; 
    private String higherOfficialId;
    private String supervisorId;
    private String pinCode;
}
