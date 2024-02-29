package com.myproject.BackendApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "appointed_workers")
public class AssignRoles {

    @Id
    private String id;
    private String profileId;
    private String role;
    
}
