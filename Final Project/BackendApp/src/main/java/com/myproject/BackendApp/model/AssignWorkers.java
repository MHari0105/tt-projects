package com.myproject.BackendApp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todays_tasks")
public class AssignWorkers {
    
    @Id
    private String id;
    // @DocumentReference(collection = "task_id")
    private String taskId;
    private List<String> workerId;
    private String status;

}
